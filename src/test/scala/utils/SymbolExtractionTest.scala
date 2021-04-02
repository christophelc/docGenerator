package utils

import org.specs2.mutable.Specification

import scala.meta.{Defn, Pat, Pkg, Source, Traverser, Tree}

class SymbolExtractionTest extends Specification with MyLogger {

  def pop(l: Seq[String]): Seq[String] = l match {
    case Nil          => Nil
    case head :: tail => tail
  }

  "Function names" should {
    "be extracted" in {
      var context = Seq[String]()
      val symbols = List.newBuilder[String]
      val path = java.nio.file.Paths
        .get(FileUtil.testResourcesPath + "root2/src/main/scala/Example", "example2.scala")
      val input       = FileUtil.toVirtualFile(path)
      val exampleTree = input.parse[Source].get

      var mutParsed = List.newBuilder[String]
      val traverser = new Traverser {
        override def apply(tree: Tree): Unit = {

          tree match {
            case pkg @ Pkg(ref, _) =>
              mutParsed += s"<-- setContext ${context.mkString(".")} -->"
              context = context :+ pkg.ref.toString()
              super.apply(pkg)
              context = pop(context)

            case defClass @ Defn.Class(_, classname, _, _, _) =>
              symbols += s"${context.mkString(".")}.${classname.toString()}"
              context = context :+ classname.toString()
              super.apply(defClass)
              context = pop(context)

            // List[scala.meta.Mod], scala.meta.Term.Name, List[meta.Type.Param], List[List[scala.meta.Term.Param]], Option[scala.meta.Type], scala.meta.Term
            case defn @ Defn.Def(_, fnName, _, paramsType, returnTypeIfExplicit, body) =>
              logger.info("xxxxxxxxxxxxxxxxxxxxxxxx")
              logger.info(paramsType.toString())
              logger.info("xxxxxxxxxxxxxxxxxxxxxxxx")
              println("********************" + symbols.result().length)
              symbols += s"${context.mkString(".")}.${fnName.toString()}"
              mutParsed += s"ctx => ${context.mkString(".")} - type => defn: $fnName"
              super.apply(defn)
            case node @ Pat.Typed(pat, typ) =>
              mutParsed += s"ctx => ${context.mkString(".")} - type => pat: $pat - type: $typ"
              super.apply(node)
            case node @ _ =>
              mutParsed += s"node => ${node.productPrefix} : $node"
              super.apply(node)
          }
        }
      }
      traverser(exampleTree)
      val s = mutParsed.result()
      logger.info(s"""\n${s.mkString("\n")}
          |""".stripMargin)
      logger.info(s"""Symbols(context, symbol)
           |${symbols.result().mkString("\n")} ----------
           |""".stripMargin)
//      println(exampleTree.syntax)
//      println("**** begin root3 example")
//      println(exampleTree.stats)
//      println("**** end root3 tree")
//      exampleTree.traverse {
//        case node =>
//          println("--")
//          println(s"[ ${node.productPrefix} --> $node ]")
//      }
//      println("**** end root3 example")
      true mustEqual (true)
    }
  }
}

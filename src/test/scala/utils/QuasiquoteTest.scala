package utils

import org.specs2.mutable.Specification
import scala.meta._

class QuasiquoteTest extends Specification with MyLogger {

  "Source code" should {
    "not be parse for a+b at the top level of a scala program" in {
      val sourceCode =
        """
          a + b
          |""".stripMargin

      val r = sourceCode.parse[Source]
      r.toString must contain("error")
    }

    "be parse for a+b inside a scala program" in {
      val sourceCode =
        """
          a + b
          |""".stripMargin

      val r = sourceCode.parse[Type]
      r.toString must not contain "error"
    }

    "be expanded by using scalameta macro q" in {
      val parsedCode =
        q"""
          object Example extends App {
              println(42)
            }
          """.structure

      parsedCode must not contain "error"
    }

    "be parsed and work with pattern matching" in {
      val result = "function(arg1, arg2)".parse[Term].get match {
        case Term.Apply(function, List(arg1, arg2)) =>
          Seq(function, arg1, arg2)
      }
      result.mkString("\n") must not contain "error"
    }
  }
  "Traversal" should {
    "show all nodes" in {
      val s = q"val x = 2".collect {
        case node => node.productPrefix -> node.toString
      }
//      logger.info(s"""
//                     ***************
//                     $s
//                     ***************
//        """)
      s must not be empty
    }
  }

  "Custon traversal" should {
    "show details of the parsed nodes" in {
      var mutParsed = List.newBuilder[String]
      val traverser = new Traverser {
        override def apply(tree: Tree): Unit = {

          tree match {
            case Pat.Var(name) =>
              mutParsed += s"var: $name (stopped here)"
            case node =>
              mutParsed += s"${node.productPrefix} : $node"
              super.apply(node)
          }
        }
      }
      traverser(q"val x = 2")
      val s = mutParsed.result()
//      logger.info(s"""
//                     ***************
//                     ${s.mkString(",")}
//                     ***************
//        """)
      s.mkString(",") must not be empty
    }
  }

  "Custom transform tree" should {
    "transform 'a + b' to 'a + function(b)" in {
      // be careful to infinite recursion; see https://scalameta.org/docs/trees/guide.html
      val transformer = new Transformer {
        override def apply(tree: Tree): Tree = tree match {
          case name @ Term.Name("b") => q"function($name)"
          case node                  => super.apply(node)
        }
      }
      val newTree = transformer(q"a + b")
      logger.info(s"""
                           ***************
                           ${newTree.toString()}
                           ***************
              """)
      newTree.toString() must contain("function(b)")
    }
  }

}

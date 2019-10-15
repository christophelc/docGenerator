package utils

import org.scalatest.FlatSpec
import org.scalatest.Matchers._
import scala.meta._

class ParserTest extends FlatSpec {

  // TODO: extract comments
  /*
  // https://github.com/scalameta/scalameta/issues/556
  def commentBefore(tree: Tree)(token: Token): Boolean = {
    token.is[Token.Comment] && token.pos.end.offset < tree.pos.start.offset
  }

  def findComment(tree: Tree, tokens: Tree): Option[Token.Comment] = {
    tokens.reverse.find(commentBefore(tree)) match {
      case comment: Option[Token.Comment] ⇒ comment
      case _ ⇒ None
    }
  }
   */
  "code under root2 folder in file Example.scala" should "be parsed" in {
    val path = java.nio.file.Paths
      .get(FileUtil.testResourcesPath + "root2/src/main/scala/Example",
           "example.scala")
    val bytes = java.nio.file.Files.readAllBytes(path)
    val text = new String(bytes, "UTF-8")
    val input = Input.VirtualFile(path.toString, text)
    val exampleTree = input.parse[Source].get
    println(exampleTree.syntax)
    println("**** begin root2 example")
    println(exampleTree.stats)
    println("**** end root2 tree")

    // see https://astexplorer.net
    exampleTree.traverse {
      // TODO: relative path to function, object, class, type
      case Pkg(ref, _) =>
        println(s"=> package ${ref} <=")
      case Defn.Class(_, classname, _, _, _) =>
        println(s"=> class ${classname} <=")
      case Defn.Object(_, objectname, _) =>
        println(s"=> object ${objectname} <=")
      /*
      case tree @ Defn.Def(_, fname, _, lParam, _, _) =>
        val comment = findComment(tree, exampleTree)
        println(s"=> function ${fname} ${lParam.toString} <=")
       */
      case node =>
        println("--")
        println(s"[ ${node.productPrefix} --> $node ]")
    }
    println("**** end root2 example")
    // no exception raised
    assert(true)
  }

  "code under root3 folder in file Example.scala with an external library (scalaz)" should "be parsed" in {
    val path = java.nio.file.Paths
      .get(FileUtil.testResourcesPath + "root3/src/main/scala/Example",
           "example.scala")
    val bytes = java.nio.file.Files.readAllBytes(path)
    val text = new String(bytes, "UTF-8")
    val input = Input.VirtualFile(path.toString, text)
    val exampleTree = input.parse[Source].get
    println(exampleTree.syntax)
    println("**** begin root3 example")
    println(exampleTree.stats)
    println("**** end root3 tree")
    exampleTree.traverse {
      case node =>
        println("--")
        println(s"[ ${node.productPrefix} --> $node ]")
    }
    println("**** end root3 example")
    // no exception raised
    assert(true)
  }
}

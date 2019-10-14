package utils

import org.scalatest.FlatSpec
import org.scalatest.Matchers._
import scala.meta._

class ParserTest extends FlatSpec {

  "code under root2 folder in file Example.scala" should "be parsed" in {
    val path = java.nio.file.Paths
      .get(FileUtil.testResourcesPath + "root2/src/main/scala/Example",
           "example.scala")
    val bytes = java.nio.file.Files.readAllBytes(path)
    val text = new String(bytes, "UTF-8")
    val input = Input.VirtualFile(path.toString, text)
    val exampleTree = input.parse[Source].get
    println(exampleTree.syntax)
    println("****")
    println(exampleTree.stats)
    println("****")
    exampleTree.traverse {
      case node =>
        println(s"[ ${node.productPrefix} --> $node ]")
    }
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
    println("****")
    println(exampleTree.stats)
    println("****")
    exampleTree.traverse {
      case node =>
        println(s"[ ${node.productPrefix} --> $node ]")
    }
    // no exception raised
    assert(true)
  }
}

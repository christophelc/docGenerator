package utils

import better.files._
import better.files.File._
import scala.meta._

object FileUtil {

  def repos(folder: String): Seq[File] = {
    folder.toFile.listRecursively
      .filter(_.isRegularFile)
      .filter(_.name.contains("build.sbt"))
      .map(_.parent)
      .toSeq
  }

  def testResourcesPath: String = {
    // not null for test environment
    getClass.getResource("/").getFile.toString
  }

  def toVirtualFile(path: java.nio.file.Path): Input.VirtualFile = {
    val bytes = java.nio.file.Files.readAllBytes(path)
    val text  = new String(bytes, "UTF-8")
    val input = Input.VirtualFile(path.toString, text)
    input
  }

}

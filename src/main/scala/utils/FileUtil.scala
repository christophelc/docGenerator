package utils

import better.files._
import better.files.File._

object FileUtil {

  def repos(folder: String): Seq[File] = {
    folder.toFile.listRecursively
      .filter(_.name.contains("build.sbt"))
      .map(_.parent)
      .toSeq
  }

  def testResourcesPath: String = {
    // not null for test environment
    getClass.getResource("/").getFile.toString
  }

}

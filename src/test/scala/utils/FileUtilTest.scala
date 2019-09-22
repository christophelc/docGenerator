package utils

import org.scalatest.FlatSpec
import org.scalatest.Matchers._

import better.files._

class FileUtilTest extends FlatSpec {

  val FolderRoot = FileUtil.testResourcesPath + "root1"

  "repo1 and repo3 having a build.sbt file" should "be listed" in {
    println(FolderRoot)
    println(FileUtil.repos(FolderRoot))
    val listReposWithBuildSbt: Set[String] =
      FileUtil
        .repos(FolderRoot)
        .map(f => f.path.getFileName.toString)
        .toSet
    listReposWithBuildSbt should equal(Set("repo1", "repo3"))
  }

}

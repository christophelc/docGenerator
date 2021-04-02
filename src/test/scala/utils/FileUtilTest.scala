package utils

import org.specs2.mutable.Specification

class FileUtilTest extends Specification {

  val folderRoot = FileUtil.testResourcesPath + "root1"

  "repo1 and repo3 having a build.sbt file" should {
    "Be listed" in {
      println(folderRoot)
      println(FileUtil.repos(folderRoot))
      val listReposWithBuildSbt: Set[String] =
        FileUtil
          .repos(folderRoot)
          .map(f => f.path.getFileName.toString)
          .toSet
      listReposWithBuildSbt mustEqual (Set("repo1", "repo3"))
    }
  }

}

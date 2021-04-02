package utils

import org.specs2.mutable.Specification

class FileUtilTest extends Specification with MyLogger {

  val folderRoot = FileUtil.testResourcesPath + "root1"

  "repo1 and repo3 having a build.sbt file" should {
    "Be listed" in {
      logger.info(s"""
          |Folder root: $folderRoot
          | repos: ${FileUtil.repos(folderRoot)}
          |""".stripMargin)
      val listReposWithBuildSbt: Set[String] =
        FileUtil
          .repos(folderRoot)
          .map(f => f.path.getFileName.toString)
          .toSet
      listReposWithBuildSbt mustEqual (Set("repo1", "repo3"))
    }
  }

}

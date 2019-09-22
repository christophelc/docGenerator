package batch

import utils.{MyLogger => Logger}
import utils.FileUtil

import scalaz._
import java.io.File

object DocGenerator extends App with Logger {

  val FolderRoot = "/home/chris/perso/dev/docGenerator/src"

  FileUtil.repos(FolderRoot).foreach(e => println(e))
  System.exit(0)
}

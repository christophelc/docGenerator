package batch

import parser._

import utils.{MyLogger => Logger}
import utils.FileUtil

object DocGenerator extends App with Logger {

  val parser: Parser = new ParserImpl()

  val home       = scala.util.Properties.envOrElse("HOME", "~")
  val folderRoot = s"$home/dev/docGenerator/src/test/resources/root3"
  println(folderRoot)

  FileUtil
    .repos(folderRoot)
    .map(f => FileUtil.toVirtualFile(f.path))
    .foreach(vf => parser.parse(vf))
  System.exit(0)
}

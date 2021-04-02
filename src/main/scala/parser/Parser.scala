package parser

import scala.meta._

trait Parser {
  def parse(vfile: Input.VirtualFile): Unit
}

class ParserImpl extends Parser {

  def parse(vfile: Input.VirtualFile): Unit = {
    println(vfile)
  }
}

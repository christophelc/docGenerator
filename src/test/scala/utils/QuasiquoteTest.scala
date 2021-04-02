package utils

import org.specs2.mutable.Specification
import scala.meta._

class QuasiquoteTest extends Specification with MyLogger {

  "Source code" should {
    "not be parse for a+b at the top level of a scala program" in {
      val sourceCode =
        """
          a + b
          |""".stripMargin

      val r = sourceCode.parse[Source]
      r.toString must contain("error")
    }

    "be parse for a+b inside a scala program" in {
      val sourceCode =
        """
          a + b
          |""".stripMargin

      val r = sourceCode.parse[Type]
      r.toString must not contain "error"
    }

    "be expanded by using scalameta macro q" in {
      val parsedCode =
        q"""
          object Example extends App {
              println(42)
            }
          """.structure

      parsedCode must not contain "error"
    }

    "xxx" in {
      val result = "function(arg1, arg2)".parse[Term].get match {
        case Term.Apply(function, List(arg1, arg2)) =>
          Seq(function, arg1, arg2)
      }
      logger.info(s"""
                     ***************
                     ${result.mkString(",")}
                     ***************
        """)
      result.mkString("\n") must not contain "error"
    }

  }
}

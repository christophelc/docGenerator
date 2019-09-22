package utils

import com.typesafe.scalalogging._

/**
  * Implement this to get a named logger in scope.
  */
trait MyLogger {

  /**
    * A named logger instance.
    */
  val logger = Logger(this.getClass)
}

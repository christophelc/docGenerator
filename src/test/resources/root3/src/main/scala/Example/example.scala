package Example

import scalaz._
import Scalaz._

object Ex {
  type Name = String

  def scalaZExample: Option[Int] = {
	Apply[Option].apply2(some(1), some(2))((a, b) => a + b)  	
  }
}
case class Ex(name: Name)

package Example

case class Ex2(name: Name) {

  def f1(a: String): String = s"$a."
  def f2(a: String, b: String)(c: String) = s"$a-$b"
  def f3: String = "do nothing"
}

case class Ex2bis(name: Name) {

  def f1bis(a: String): String = s"$a."
  def f2bis(a: String, b: String)(c: String) = s"$a-$b"
  def f3bis: String = "do nothing"
}
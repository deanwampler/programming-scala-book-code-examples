// src/script/scala/progscala3/patternmatching/UnapplyBoolean.scala

object ScalaSearch:                                                  // <1>
  def unapply(s: String): Boolean = s.toLowerCase.contains("scala")

val books = Seq(
  "Programming Scala",
  "JavaScript: The Good Parts",
  "Scala Cookbook").zipWithIndex   // add an "index"

val result = for s <- books yield s match                            // <2>
  case (_ as ScalaSearch(), index) => s"$index: found Scala"
  case (_, index) => s"$index: no Scala"

assert(result == Seq("0: found Scala", "1: no Scala", "2: found Scala"))

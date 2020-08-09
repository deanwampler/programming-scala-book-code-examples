// src/script/scala/progscala3/patternmatching/UnapplyBoolean.scala

object ScalaSearch:
	def unapply(s: String): Boolean = s.toLowerCase.contains("scala")

val books = Seq(
	"Programming Scala",
	"JavaScript: The Good Parts",
	"Scala Cookbook")

val result = for s <- books yield s match
	case s @ ScalaSearch() => s"Found Scala: $s"
	case s => s"Insufficient Scala: $s"

assert(result == Seq("even", "odd", "even", "odd", "even"))

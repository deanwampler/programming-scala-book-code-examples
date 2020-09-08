// src/script/scala/progscala3/patternmatching/UnapplyProduct.scala

class Words(words: Seq[String], index: Int) extends Product:         // <1>
  def _1 = words                                                     // <2>
  def _2 = index

  def canEqual(that: Any): Boolean = ???                             // <3>
  def productArity: Int = ???
  def productElement(n: Int): Any = ???

object Words:
  def unapply(si: (String, Int)): Words =                            // <4>
    val words = si._1.split("""\W+""").toSeq                         // <5>
    new Words(words, si._2)

val books = Seq(
  "Programming Scala",
  "JavaScript: The Good Parts",
  "Scala Cookbook").zipWithIndex   // add an "index"

val result = books.map {
  case Words(words, index) => s"$index: count = ${words.size}"
}
assert(result == Seq("0: count = 2", "1: count = 4", "2: count = 2"))

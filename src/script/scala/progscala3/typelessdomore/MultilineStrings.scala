// src/script/scala/progscala3/typelessdomore/MultilineStrings.scala

def hello(name: String) = s"""Welcome!
  Hello, $name!
  * (Gratuitous Star!!)
  |We're glad you're here.
  |  Have some extra whitespace.""".stripMargin

val hi = hello("Programming Scala")

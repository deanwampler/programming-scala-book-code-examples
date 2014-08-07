// src/main/scala/progscala2/typelessdomore/multiline-strings.sc

def hello(name: String) = s"""Welcome!
  Hello, $name!
  * (Gratuitous Star!!)
  |We're glad you're here.
  |  Have some extra whitespace.""".stripMargin

hello("Programming Scala")

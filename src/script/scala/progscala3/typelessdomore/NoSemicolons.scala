// src/script/scala/progscala3/typelessdomore/NoSemicolons.scala

val s = "hello"
  + "world"
  + "!"

class Foo(name: String):
  def yell: String = name.toUpperCase + "!"

def equalsign(s: String) =
  println("equalsign: " + s)

def equalsign2(s: String) = {
  println("equalsign2: " + s)
}

def commas(s1: String,
           s2: String) = Console.
  println("comma: " + s1 +
          ", " + s2)

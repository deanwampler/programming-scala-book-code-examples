// src/script/scala/progscala3/BracesSyntax.scala

// For completeness, Scala 2 imports. Still allowed in Scala 3.0, but not
// with the -source:future flag we use in SBT:
import scala.concurrent._
import java.util.{Queue => JQueue}
import java.util.{HashMap => _, _}

// Braces syntax - valid in both Scala 2 and 3
// Braces around single expressions can be omitted, but they
// are shown to represent the general case.

// Package definition.
// Commented out because we can't declare a package in a script!
// package mypkg {
//   stuff under mypkg...
// }

// For comprehension
val evens = for {
  i <- 0 until 10
  if i%2 == 0
} yield { i }

// For loop
for {   // Can replace with () if there is a single generator: i <- ...
  i <- 0 until 10
  if i%2 == 0
} { println(i) }

if (8 < 10) {
  println(true)
} else {
  println(false)
}

// While loop
var i = 0
while (i < 10) { i+=1 }

// Match expression
var x: Int = 0
x match {
  case 0 => println("zero")
  case _ => println("other value") 
}

// Partially-defined function
val opt: Option[Int] => Int = {
  case Some(i) => i
  case None => 0
}

// Try, catch, finally expression (contrived...)
import scala.util.control.NonFatal
var string: Option[Int] = None
try {
  string = Some(Integer.parseInt("foo"))
  println(s"Parsed \"foo\" to ${string}")
} catch {
  case NonFatal(ex) => println(s"NonFatal thrown: ${ex}")
} finally {
  if (string == None) {
    println("string is None")
  }
}

// Multiline method definition
def multiline(s: String): String = {
  println(s"input: $s")
  val result = s.toUpperCase
  println(s"output: $result")
  result
}

// Trait, class, and object definition
trait Monoid[A] {
  def add(a1: A, a2: A): A
  def zero: A
}

// Instantiate an anonymous instance
val mon = new Monoid[Int] {
  def add(i1: Int, i2: Int): Int = i1+i2
  def zero: Int = 0
}

// New type class given instantiation
given intMonoid: Monoid[Float] {
  def add(f1: Float, f2: Float): Float = f1+f2
  def zero: Float = 0.0F
}

// Alias given
given Monoid[String] = new Monoid[String] {
  def add(s1: String, s2: String): String = s1+s2
  def zero: String = ""
}

// Extension method definition
extension (s: String) {
  def bold: String = s.toUpperCase + "!"
  def meek: String = s"(${s.toLowerCase}, maybe?)"
}

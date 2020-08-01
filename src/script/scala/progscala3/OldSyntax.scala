// Old syntax

// For comprehension
val evens = for {
  i <- 0 until 10
  if i%2 == 0
} yield i

// For loop
for {
  i <- 0 until 10
  if i%2 == 0
} println(i)

if (8 < 10) {
  println(true)
} else {
  println(false)
}

// While loop
var i = 0
while (i < 10) { i+=1 }

// Match expression
0 match {
  case 0 => println("zero")
  case _ => println("other value")
}

// Partially-defined function
val o:Option[Int] => Int = {
  case Some(i) => i
  case None => 0
}

// Try, catch, finally expression
import scala.io.Source
import scala.util.control.NonFatal
var source: Option[Source] = None
try {
  source = Some(Source.fromFile("..."))
  // do something with it
} catch {
  case NonFatal(ex) => println(ex)
} finally {
  if (source != None) {
    source.get.close
  }
}

// Multiline method definition
def multiline(s: String): String = {
  println(s"input: $s")
  val result = s.toUpperCase
  println(s"output: $result")
  result
}

// Package definition
package mypkg {
  // ...
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

// New type class definition
given intMonoid as Monoid[Int] {
  def add(i1: Int, i2: Int): Int = i1+i2
  def zero: Int = 0
}

// Extension method definition
extension (s: String) {
  def bold: String = s.toUpperCase + "!"
  def meek: String = "(" + s.toLowerCase + ", maybe?)"
}

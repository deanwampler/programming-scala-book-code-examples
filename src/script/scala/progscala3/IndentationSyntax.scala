// src/script/scala/progscala3/IndentationSyntax.scala

// For completeness, Scala 3 imports:
import scala.concurrent.{given, *}
import java.util.Queue as JQueue
import java.util.{HashMap as _, *}

// Significant indentation syntax.
// All the +end identifier+ markers are optional!

// Package definition
// Commented out because we can't declare a package in a script!
// package mypkg:
//   stuff under mypkg...
// end mypkg

// For comprehension
val evens = for
  i <- 0 until 10
  if i%2 == 0
yield i

// For loop using the new control syntax with "do"
for
  i <- 0 until 10
  if i%2 == 0
do println(i)

for
  i <- 0 until 10
  if i%2 == 0
do      // For longer do blocks.
  println(i)
end for

// If expression using the new control syntax with "then"
if 8 < 10 then
  println(true)
else
  println(false)
end if

// While loop using the new control syntax with "do"
var i = 0
while i < 10 do i+=1

while i < 20
do     // For longer do blocks.
  i+=1
end while

// Match expression
0 match
  case 0 => println("zero")
  case _ => println("other value")
end match

// Partially-defined function
val opt: Option[Int] => Int =
  case Some(i) => i
  case None => 0
end opt

// Try, catch, finally expression
import scala.io.Source
import scala.util.control.NonFatal
var source: Option[Source] = None
try
  source = Some(Source.fromFile("README.md"))
  // ...
catch
  case NonFatal(ex) => println(ex)
finally
  if source != None then
    source.get.close
  end if
end try

// Multiline method definition.
def multiline(s: String): String =
  println(s"input: $s")
  val result = s.toUpperCase
  println(s"output: $result")
  result
end multiline  // for constructors, use their name: this

// Multiline value definition
val foo =
  println("Hi World!")
  42
end foo  // val doesn't work in this case, but...

val (foo, bar) =
  "foo" -> "bar"
end val  // here's when you use val.

// Trait, class, and object definition
trait Monoid[A]:
  def add(a1: A, a2: A): A
  def zero: A
end Monoid

// Instantiate an anonymous instance
val intMon = new Monoid[Int]:
    def add(i1: Int, i2: Int): Int = i1+i2
    def zero: Int = 0
  // Can't use end ... here because of the first line, but...
val longMon =
  new Monoid[Long]:
    def add(l1: Long, l2: Long): Long = l1+l2
    def zero: Long = 0L
  end new // You can use "end new" here because "new Monoid..." starts at the same column!

// New type class given instantiation
given floatMonoid: Monoid[Float] with
  def add(f1: Float, f2: Float): Float = f1+f2
  def zero: Float = 0.0F
end floatMonoid  // Use identifier.
given Monoid[Double] with
  def add(d1: Double, d2: Double): Double = d1+d2
  def zero: Double = 0.0
end given        // Anonymous, so no identifier. Hence, use "given".

// Alias given
given Monoid[String] = new Monoid[String]:
  def add(s1: String, s2: String): String = s1+s2
  def zero: String = ""
end given

// Extension method definition
extension (s: String)
  def bold: String = s.toUpperCase + "!"
  def meek: String = s"(${s.toLowerCase}, maybe?)"
end extension

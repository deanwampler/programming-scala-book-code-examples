// src/script/scala/progscala3/IndentationSyntax.scala

// For completeness, Scala 3 imports:
import scala.concurrent.{given, *}
import java.util.Queue as JQueue
import java.util.{HashMap as JHashMap, HashSet as _}

// Significant indentation syntax

// Package definition
// package mypkg:   // Can't declare a package in a script!
  // stuff under mypkg...

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

// If expression using the new control syntax with "then"
if 8 < 10 then
  println(true)
else
  println(false)

// While loop using the new control syntax with "do"
var i = 0
while i < 10 do { i+=1 }

// Match expression
0 match
  case 0 => println("zero")
  case _ => println("other value")

// Partially-defined function
val o:Option[Int] => Int =
  case Some(i) => i
  case None => 0

// Try, catch, finally expression
import scala.io.Source
import scala.util.control.NonFatal
var source: Option[Source] = None
try
  source = Some(Source.fromFile("..."))
  // do something with it
catch
  case NonFatal(ex) => println(ex)
finally
  if source != None then
    source.get.close

// Multiline method definition
def multiline(s: String): String =
  println(s"input: $s")
  val result = s.toUpperCase
  println(s"output: $result")
  result

// Trait, class, and object definition
trait Monoid[A]:
  def add(a1: A, a2: A): A
  def zero: A

// Instantiate an anonymous instance
val mon = new Monoid[Int]:
  def add(i1: Int, i2: Int): Int = i1+i2
  def zero: Int = 0

// New type class definition
given intMonoid: Monoid[Int] with
  def add(i1: Int, i2: Int): Int = i1+i2
  def zero: Int = 0

// Extension method definition
extension (s: String)
  def bold: String = s.toUpperCase + "!"
  def meek: String = "(" + s.toLowerCase + ", maybe?)"

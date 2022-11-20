// src/script/scala/progscala3/IndentationSyntax-FewerBraces.scala

// Scala 3.2 introduced an experimental feature to allow indentation syntax
// in more situations, instead of braces. This extension will be offered as
// a supported feature in 3.3. This file explores the differences, where each
// example is repeated twice, once with braces as required for Scala 3.0 to 3.2
// and again with the new syntax that eliminates the braces.
//
// In essence, a `:` (colon) token is also recognized where a function 
// argument would be expected. The examples below are adapted from the Dotty
// documentation page:
//   https://dotty.epfl.ch/docs/reference/other-new-features/indentation.html

// The next import is required for pre-3.3 releases, but not for 3.3+.
// However, Scala disallows experimental imports except in _snapshot_ builds of 
// Scala itself. Hence, you'll need to build a snapshot of Scala and run the 
// interpreter with it to try the braceless examples below:
// 1. Clone the Dotty repo: https://github.com/lampepfl/dotty
// 2. Run `sbt dist/packArchive`
// 3. Copy the `*.zip` or `*.tar.gz` file created in `dist/target` somewhere convenient 
// 4. Expand the archive in the new directory
// 5. Run `.../bin/scala` 
// 6. Copy and paste the following examples.

import language.experimental.fewerBraces

// A helper method:

def times(n: Int)(f: => Unit): Unit =
  for i <- 0 until n do f

// Use braces to define and pass the function:
times(3) {
  println("one")
  println("two")
}
// (Output shown as comments)
// one
// two
// one
// two
// one
// two

// New braceless syntax to define and pass the function. Now the compiler
// interprets the trailing colon, followed by indented lines, as the 
// beginning and definition of the anonymous function:
times(3):
  println("one")
  println("two")
// one
// two
// ...

import java.io.File
val dir = new File(".")
// val dir: java.io.File = .

// Another example, where the `++` method expects a function argument:
val paths1 = Seq(dir) `++` {
  dir.listFiles
}
// val paths1: Seq[java.io.File] = List(., ./scala3-3.3.0-RC1-bin-SNAPSHOT, ./scala3-3.3.0-RC1-bin-SNAPSHOT.zip)

val paths2 = Seq(dir) `++`:
  dir.listFiles
// val paths2: Seq[java.io.File] = List(., ./scala3-3.3.0-RC1-bin-SNAPSHOT, ./scala3-3.3.0-RC1-bin-SNAPSHOT.zip)

val xs = 0 until 10
// val xs: Range = Range 0 until 10

// What about function arguments? 
// The function arguments can either go on the next line after the colon
// or on the same line:
val map1a = xs.map {
  x =>
    val y = x - 1
    y * y
}
// val map1a: IndexedSeq[Int] = Vector(1, 0, 1, 4, 9, 16, 25, 36, 49, 64)

val map2a = xs.map:
  x =>
    val y = x - 1
    y * y
// val map2a: IndexedSeq[Int] = Vector(1, 0, 1, 4, 9, 16, 25, 36, 49, 64)

val map1b = xs.map { x =>
  val y = x - 1
  y * y
}
// val map1b: IndexedSeq[Int] = Vector(1, 0, 1, 4, 9, 16, 25, 36, 49, 64)

val map2b = xs.map: x =>
  val y = x - 1
  y * y
// val map2b: IndexedSeq[Int] = Vector(1, 0, 1, 4, 9, 16, 25, 36, 49, 64)

// It looks odd, but the arrow can be on the next line, separated from the arguments:
val map3b = xs.map: x
  =>
    val y = x - 1
    y * y
// val map3b: IndexedSeq[Int] = Vector(1, 0, 1, 4, 9, 16, 25, 36, 49, 64)

// Here are multiple arguments:

val fold1a = xs.foldLeft(0) { (x, y) =>
   x + y
}
// val fold1: Int = 45

val fold2a = xs.foldLeft(0): (x, y) =>
   x + y
// val fold2a: Int = 45

// BUT, you can't put the function body on the same line:
// scala> val fold2b = xs.foldLeft(0): (x, y) => x + y
//      |
// -- Error: ---------------------------------------------------------------------------------------------------------------------------------------------------
// 1 |val fold2b = xs.foldLeft(0): (x, y) => x + y
//   |             ^^^^^^^^^^^^^^^^^^^^^^
//   |             not a legal formal parameter for a function literal

// You'll have to use braces:
val fold1b = xs.foldLeft(0) {(x, y) => x + y}
// val fold1b: Int = 45

// So, anonymous functions no longer require braces. What about import statements?
// First, here's what we know works:

import scala.util.{Try, Success, Failure}
import scala.{
  Option, Some, None
}

// However, there is no braceless alternative: 
// scala> import scala.util:    # : instead of .??
//      |  Try, Success, Failure
//      |
// -- Error: ---------------------------------------------------------------------------------------------------------------------------------------------------
// 1 |import scala.util:
//   |                 ^
//   |                 end of statement expected but ':' found

// scala> import scala.util.:   # : after .??
//      |  Try, Success, Failure
//      |
// -- [E040] Syntax Error: -------------------------------------------------------------------------------------------------------------------------------------
// 1 |import scala.util.:
//   |                  ^
//   |                  an identifier expected, but ':' found
//   |
//   | longer explanation available when compiling with `-explain`
// -- [E040] Syntax Error: -------------------------------------------------------------------------------------------------------------------------------------
// 2 | Try, Success, Failure
//   |             ^
//   |             '.' expected, but ',' found
// -- [E040] Syntax Error: -------------------------------------------------------------------------------------------------------------------------------------
// 3 |
//   |^
//   |'.' expected, but eof found

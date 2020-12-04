// src/script/scala/progscala3/meta/compiletime/RequireConst.scala
// Example mentioned in the book, but not discussed there.
import scala.compiletime.requireConst

requireConst(1)
requireConst(1+2)
requireConst("foo")
requireConst("foo"+"bar")  // Okay
val x = "foo"
requireConst(x)            // ERROR

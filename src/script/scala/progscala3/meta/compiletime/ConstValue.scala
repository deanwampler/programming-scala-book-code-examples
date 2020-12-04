// src/script/scala/progscala3/meta/compiletime/ConstValue.scala
// Example mentioned in the book, but not discussed there.
import scala.compiletime.{constValue, constValueOpt}

// Verify that the types in [...] are constants. The *Opt variant won't throw
// an exception if the expression is not a constant, but return None.

def tryInt =
  import compiletime.ops.int._   // Scope this import to this method
  assert(constValue[1] == 1)
  assert(constValueOpt[1] == Some(1))

  assert(constValueOpt[1+2] == Some(3))
  assert(constValue[1+2] == 3)

def tryBoolean =
  import compiletime.ops.boolean._
  assert(constValue[true] == true)
  assert(constValueOpt[true && false] == Some(false))

def tryString =
  import compiletime.ops.string._
  assert(constValue["foo"] == "foo")
  assert(constValueOpt["foo"] == Some("foo"))

  assert(constValue["foo"+"bar"] == "foobar")
  assert(constValueOpt["foo"+"bar"] == Some("foobar"))

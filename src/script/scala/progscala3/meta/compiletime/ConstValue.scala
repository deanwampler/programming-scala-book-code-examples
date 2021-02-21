// src/script/scala/progscala3/meta/compiletime/ConstValue.scala
// Example mentioned in the book, but not discussed there.
import scala.compiletime.{constValue, constValueOpt, constValueTuple}

// Verify that the types in [...] are constants. The *Opt variant won't throw
// an exception if the expression is not a constant, but return None.

def tryInt =
  import compiletime.ops.int.*   // Scope this. import to this method
  assert(constValue[1] == 1)
  assert(constValueOpt[1] == Some(1))
  assert(constValue[1+2] == 3)
  assert(constValueOpt[1+2] == Some(3))
  assert(constValueTuple[(1+2,3*4)] == (3,12))

def tryBoolean =
  import compiletime.ops.boolean.*
  assert(constValue[true] == true)
  assert(constValueOpt[true] == Some(true))
  assert(constValueOpt[![true]] == Some(false))  // The [] required!
  assert(constValue[true  && true]  == true)
  assert(constValue[true  && false] == false)
  assert(constValue[false && true]  == false)
  assert(constValue[false && false] == false)
  assert(constValue[true  || true]  == true)
  assert(constValue[true  || false] == true)
  assert(constValue[false || true]  == true)
  assert(constValue[false || false] == false)
  assert(constValue[true   ^ true]  == false)    // Exclusive Or
  assert(constValue[true   ^ false] == true)
  assert(constValue[false  ^ true]  == true)
  assert(constValue[false  ^ false] == false)
  assert(constValueTuple[(true || false, true && false)] == (true, false))

def tryString =
  import compiletime.ops.string.*
  assert(constValue["foo"] == "foo")
  assert(constValueOpt["foo"] == Some("foo"))

  assert(constValue["foo"+"bar"] == "foobar")
  assert(constValueOpt["foo"+"bar"] == Some("foobar"))

  assert(constValueTuple[("foo","bar")] == ("foo", "bar"))

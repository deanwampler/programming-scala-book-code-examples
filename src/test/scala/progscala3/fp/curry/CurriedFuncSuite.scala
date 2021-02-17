// src/test/scala/progscala3/fp/curry/CurriedFuncSuite.scala
package progscala3.fp.curry

import munit.*

class CurriedFuncSuite extends FunSuite:

  def cat1(s1: String)(s2: String) = s1 + s2
  def cat2(s1: String) = (s2: String) => s1 + s2
  def cat3(s1: String, s2: String) = s1 + s2

  test("Methods behave like functions for currying"){
    assert(cat1("hello")("world") == cat2("hello")("world"))
  }

  test("Two-parameter methods can be curried into two param-list methods") {
    val catCurried = cat3.curried
    // catCurried: String => (String => String) = <function1>

    assert(catCurried("hello")("world") == "helloworld")
    assert(cat3("hello", "world") == "helloworld")
  }

  // TODO. The uncurried line fails to compile, but it looks like
  // either a dotc bug or munit macro bug. Test programs that do
  // the same thing work fine.
  // test("A curried method can be uncurried") {
  //   val cat1Uncurried = Function.uncurried(cat1)
  //   assert(cat1Uncurried("hello", "world") == "helloworld")
  // }

  val f1: String =>  String => String
    = (s1: String) => (s2: String) => s1 + s2
  val f2: String => (String => String)
    = (s1: String) => (s2: String) => s1 + s2

  test("Curried function arguments bind right to left") {
    assert(f1("hello")("world") == "helloworld")
    assert(f2("hello")("world") == "helloworld")

    val ff1 = Function.uncurried(f1)
    val ff2 = Function.uncurried(f2)

    assert(ff1("hello", "world") == "helloworld")
    assert(ff2("hello", "world") == "helloworld")
  }

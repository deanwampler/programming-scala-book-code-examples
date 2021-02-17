// src/test/scala/progscala3/contexts/TypeClassesSubtypingSuite.scala
package progscala3.contexts

import munit.*

class TypeClassesSubtypingSuite extends FunSuite:

  trait Stringizer[+T]:
    def stringize: String

  implicit class AnyStringizer(a: Matchable) extends Stringizer[Matchable]:
    def stringize: String = a match
      case s: String => s
      case i: Int => (i*10).toString
      case f: Float => (f*10).toString
      case other =>
        throw UnsupportedOperationException(s"Can't stringize $other")

  case class Person(name: String, age: Int)

  val seq: Seq[Matchable] = Seq(1, 2.2F, "three", Person("me", 20))

  val strings = seq map { (x: Matchable) =>
    try
      s"$x: ${x.stringize}"
    catch
      case e: java.lang.UnsupportedOperationException => e.getMessage
  }

  test("Another way to do type classing") {
    assert(strings ==
      Seq("1: 10", "2.2: 22.0", "three: three", "Can't stringize Person(me,20)"))
  }

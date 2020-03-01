// src/test/scala/progscala3/implicits/TypeClassesSubtypingSuite.scala
package progscala3.implicits

import munit._
import scala.language.implicitConversions

class TypeClassesSubtypingSuite extends FunSuite {

  trait Stringizer[+T] {
    def stringize: String
  }

  implicit class AnyStringizer(a: Any) extends Stringizer[Any] {
    def stringize: String = a match {
      case s: String => s
      case i: Int => (i*10).toString
      case f: Float => (f*10).toString
      case other =>
        throw new UnsupportedOperationException(s"Can't stringize $other")
    }
  }

  val seq: Seq[Any] = Seq(1, 2.2F, "three", Symbol("sym"))

  val strings = seq map { (x:Any) =>
    try {
      s"$x: ${x.stringize}"
    } catch {
      case e: java.lang.UnsupportedOperationException => e.getMessage
    }
  }

  test("Another way to do type classing") {
    assert(strings ==
      Seq("1: 10", "2.2: 22.0", "three: three", "Can't stringize 'sym"))
  }
}

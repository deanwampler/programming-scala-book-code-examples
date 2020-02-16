// src/main/scala/progscala3/basicoop/ValueClassPhoneNumberSpec.scala
package progscala3

import munit._
import scala.reflect.ClassTag

trait FunSuite2 extends FunSuite {

  def test(description: String)(body: => Any): Unit =
    test(opt(description))(body)(Location.empty)

  def intercept2[T <: Throwable](body: => Any)(implicit T: ClassTag[T]) =
    intercept(body)(implicitly[ClassTag[T]], Location.empty)

  def opt(s:String): TestOptions = new TestOptions(
    s, Set.empty, Location.empty)
}

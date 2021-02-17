// src/main/scala/progscala3/meta/UsingClassTagViews.scala
package progscala3.meta

import scala.reflect.ClassTag

object UsingClassTagViews:

  /**
   * A method that makes a sequence of the proper
   * element type, by leveraging a ClassTag view of
   * the element type.
   */
  def mkSeq[T : ClassTag](elems: T*) = Seq[T](elems*)

@main def TryUsingClassTagViews =
  import UsingClassTagViews.*
  assert(mkSeq(1, 2, 3) == Seq[Int](1, 2, 3))
  assert(mkSeq("one", "two", "three") == Seq[String]("one", "two", "three"))
  assert(mkSeq(1, "two", 3.14) == Seq[Any](1, "two", 3.14))          // <1>
  println("success!")

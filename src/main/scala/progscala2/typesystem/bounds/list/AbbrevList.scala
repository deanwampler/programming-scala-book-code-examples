// src/main/scala/progscala2/typesystem/bounds/list/AbbrevList.scala
// Loosely adapted from scala/List.scala in the Scala library.
package progscala2.typesystem.bounds.list

sealed abstract class AbbrevList[+A] {

  def isEmpty: Boolean
  def head: A
  def tail: AbbrevList[A]

  def ::[B >: A] (x: B): AbbrevList[B] =
    new progscala2.typesystem.bounds.list.::(x, this)

  final def foreach(f: A => Unit) = {
    var these = this
    while (!these.isEmpty) {
      f(these.head)
      these = these.tail
    }
  }
}

// The empty AbbrevList.

case object AbbrevNil extends AbbrevList[Nothing] {
  override def isEmpty = true

  def head: Nothing =
    throw new NoSuchElementException("head of empty AbbrevList")

  def tail: AbbrevList[Nothing] =
    throw new NoSuchElementException("tail of empty AbbrevList")
}

// A non-empty AbbrevList characterized by a head and a tail.

final case class ::[B](private var hd: B,
    private[list] var tl: AbbrevList[B]) extends AbbrevList[B] {

  override def isEmpty: Boolean = false
  def head : B = hd
  def tail : AbbrevList[B] = tl
}

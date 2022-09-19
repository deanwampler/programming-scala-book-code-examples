// src/main/scala/progscala3/typesystem/bounds/list/AbbrevList.scala
// Loosely adapted from scala/List.scala in the Scala library.
package progscala3.typesystem.bounds.list
import scala.annotation.targetName
import java.util.NoSuchElementException

/**
 * Scala 3.2 no longer allows top-level classes to have @targetName annotations.
 * From the error message printed by the compiler:
 *   This restriction is due to the naming convention of Java classfiles, whose filenames
 *   are based on the name of the class defined within. If @targetName were permitted
 *   here, the name of the classfile would be based on the target name, and the compiler
 *   could not associate that classfile with the Scala-visible defined name of the class.
 * One solution is to wrap the types in an object, as done here, which is not shown in the book.
 */  
object AbbrevListModule:
  sealed abstract class AbbrevList[+A]:

    def isEmpty: Boolean
    def head: A
    def tail: AbbrevList[A]

    @targetName("prepend")
    def ::[B >: A] (x: B): AbbrevList[B] = new ::(x, this)

    final def foreach(f: A => Unit) =
      var these = this
      while !these.isEmpty do
        f(these.head)
        these = these.tail

  // The empty AbbrevList.

  case object AbbrevNil extends AbbrevList[Nothing]:
    override def isEmpty = true

    def head: Nothing =
      throw NoSuchElementException("head of empty AbbrevList")

    def tail: AbbrevList[Nothing] =
      throw NoSuchElementException("tail of empty AbbrevList")

  // A non-empty AbbrevList characterized by a head and a tail.

  @targetName("AbbrevListCons")
  final case class ::[B](private var hd: B,
      private[list] var tl: AbbrevList[B]) extends AbbrevList[B]:

    override def isEmpty: Boolean = false
    def head : B = hd
    def tail : AbbrevList[B] = tl

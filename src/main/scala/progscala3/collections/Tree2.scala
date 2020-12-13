// src/main/scala/progscala3/collections/Tree2.scala
// This example does not appear in the book. See also Tree.scala and Tree3.scala.
package progscala3.collections

trait Tree2[A,AL,AR]:
  val value: A
  def leftOption: Option[Tree2[AL,?,?]]
  def rightOption: Option[Tree2[AR,?,?]]

case class Leaf[A](value: A) extends Tree2[A,Nothing,Nothing]:
  def leftOption: Option[Tree2[Nothing,Nothing,Nothing]] = None
  def rightOption: Option[Tree2[Nothing,Nothing,Nothing]] = None

case class Branch[A,AL,AR](value: A, left: Tree2[AL,?,?], right: Tree2[AR,?,?]) extends Tree2[A,AL,AR]:
  def leftOption: Option[Tree2[AL,?,?]] = Some(left)
  def rightOption: Option[Tree2[AR,?,?]] = Some(right)

case class Left[A,AL](value: A, left: Tree2[AL,?,?]) extends Tree2[A,AL,Nothing]:
  def leftOption: Option[Tree2[AL,?,?]] = Some(left)
  def rightOption: Option[Tree2[Nothing,Nothing,Nothing]] = None

case class Right[A,AR](value: A, right: Tree2[AR,?,?]) extends Tree2[A,Nothing,AR]:
  def leftOption: Option[Tree2[Nothing,Nothing,Nothing]] = None
  def rightOption: Option[Tree2[AR,?,?]] = Some(right)

def tryTree2() =
  Branch(1,
    Right(1.1,
      Branch("111",
        Leaf(1111),
        Left(1112,
          Right(1112.1,
            Leaf("111212"))))),
    Branch(1.2,
      Leaf("121"),
      Right("122",
        Leaf(1222))))

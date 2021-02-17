// src/main/scala/progscala3/collections/Tree.scala
// This example does not appear in the book. See also Tree2.scala and Tree3.scala.
package progscala3.collections

case class Tree[+A](val value: A, val left: Option[Tree[?]], val right: Option[Tree[?]]):
  override def toString: String = (left,right) match
    case (None, None) => s"Leaf($value)"
    case _ => s"Tree($value, $left, $right)"

object Tree:
  def leaf[A](value: A): Tree[A] = Tree[A](value, None, None)
  def branch[A](value: A, left: Tree[?], right: Tree[?]): Tree[A] =
    Tree(value, Some(left), Some(right))
  def left[A](value: A, left: Tree[?]): Tree[A] =
    Tree(value, Some(left), None)
  def right[A](value: A, right: Tree[?]): Tree[A] =
    Tree[A](value, None, Some(right))

def tryTree() =
  import Tree.*
  val tree =
    branch(1,
      right(1.1,
        branch("111",
          leaf(1111),
          left(1112,
            right(1112.1,
              leaf("111212"))))),
      branch(1.2,
        leaf("121"),
        right("122",
          leaf(1222))))

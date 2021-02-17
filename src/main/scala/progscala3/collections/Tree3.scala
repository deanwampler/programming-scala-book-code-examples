// src/main/scala/progscala3/collections/Tree3.scala
// This example does not appear in the book. See also Tree.scala and Tree2.scala.
package progscala3.collections

case class Tree3[+A,AL,AR] private (
    value: A, left: Option[Tree3[AL,?,?]], right: Option[Tree3[AR,?,?]]):
  override def toString: String = (left, right) match
    case (None, None) => s"Leaf($value)"
    case (Some(l), None) => s"Left($value, $l)"
    case (None, Some(r)) => s"Right($value, $r)"
    case (Some(l), Some(r)) => s"Branch($value, $l, $r)"

object Tree3:
  def leaf[A](value: A): Tree3[A,Unit,Unit] = Tree3[A,Unit,Unit](value, None, None)
  def branch[A,AL,AR](value: A, left: Tree3[AL,?,?], right: Tree3[AR,?,?]): Tree3[A,AL,AR] =
    Tree3(value, Some(left), Some(right))
  def left[A,AL](value: A, left: Tree3[AL,?,?]): Tree3[A,AL,Unit] =
    Tree3(value, Some(left), None)
  def right[A,AR](value: A, right: Tree3[AR,?,?]): Tree3[A,Unit,AR] =
    Tree3(value, None, Some(right))

def tryTree3() =
  import Tree3.*
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

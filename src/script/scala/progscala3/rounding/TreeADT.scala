// src/script/scala/progscala3/rounding/TreeADT.scala

object Scala2ADT:
  sealed trait Tree[T]                                          // <1>
  final case class Branch[T](                                   // <2>
    left: Tree[T], right: Tree[T]) extends Tree[T]
  final case class Leaf[T](elem: T) extends Tree[T]             // <3>

  val tree = Branch(
    Branch(
      Leaf(1),
      Leaf(2)),
    Branch(
      Leaf(3),
      Branch(Leaf(4),Leaf(5))))

object Scala3ADT:
  enum Tree[T]:                                                 // <4>
    case Branch(left: Tree[T], right: Tree[T])
    case Leaf(elem: T)

  import Tree._                                                 // <5>
  val tree = Branch(
    Branch(
      Leaf(1),
      Leaf(2)),
    Branch(
      Leaf(3),
      Branch(Leaf(4),Leaf(5))))

Scala2ADT.tree                                                  // <6>
Scala3ADT.tree


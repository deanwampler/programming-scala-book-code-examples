// src/script/scala/progscala3/rounding/TreeADT.scala

object Scala2ADT:                                                     // <1>
  sealed trait Tree[T]                                                // <2>
  case class Branch[T](left: Tree[T], right: Tree[T]) extends Tree[T] // <3>
  case class Leaf[T](elem: T) extends Tree[T]                         // <4>

  val tree = Branch(
    Branch(
      Leaf(1),
      Leaf(2)),
    Branch(
      Leaf(3),
      Branch(Leaf(4),Leaf(5))))

object Scala3ADT:                                                     // <5>
  enum Tree[T]:                                                       // <6>
    case Branch(left: Tree[T], right: Tree[T])
    case Leaf(elem: T)

  import Tree._                                                       // <7>
  val tree = Branch(
    Branch(
      Leaf(1),
      Leaf(2)),
    Branch(
      Leaf(3),
      Branch(Leaf(4),Leaf(5))))

Scala2ADT.tree                                                        // <8>
Scala3ADT.tree
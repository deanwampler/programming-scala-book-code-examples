// src/script/scala/progscala3/rounding/TreeADT.scala

object SealedADT:
  sealed trait Tree[T]                                          // <1>
  case class Branch[T](                                         // <2>
    left: Tree[T], right: Tree[T]) extends Tree[T]
  case class Leaf[T](elem: T) extends Tree[T]                   // <3>

  val tree = Branch(
    Branch(
      Leaf(1),
      Leaf(2)),
    Branch(
      Leaf(3),
      Branch(Leaf(4),Leaf(5))))

object EnumADT:
  enum Tree[T]:                                                 // <4>
    case Branch(left: Tree[T], right: Tree[T])
    case Leaf(elem: T)

  import Tree.*                                                 // <5>
  val tree = Branch(
    Branch(
      Leaf(1),
      Leaf(2)),
    Branch(
      Leaf(3),
      Branch(Leaf(4),Leaf(5))))

SealedADT.tree                                                  // <6>
EnumADT.tree


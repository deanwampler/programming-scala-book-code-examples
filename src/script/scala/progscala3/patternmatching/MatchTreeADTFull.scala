// src/script/scala/progscala3/patternmatching/MatchTreeADT.scala

sealed trait Tree2[T]                                           // <1>
case class Branch2[T](left: Tree2[T], right: Tree2[T]) extends Tree2[T]
case class Leaf2[T](elem: T) extends Tree2[T]

enum Tree3[T]:                                                  // <2>
  case Branch3(left: Tree3[T], right: Tree3[T])
  case Leaf3(elem: T)

val tree2 = Branch2(
  Branch2(
    Leaf2(1),
    Leaf2(2)),
  Branch2(
    Leaf2(3),
    Branch2(Leaf2(4),Leaf2(5))))

import Tree3._
val tree3 = Branch3(
  Branch3(
    Leaf3(1),
    Leaf3(2)),
  Branch3(
    Leaf3(3),
    Branch3(Leaf3(4),Leaf3(5))))

val result = for
  tree <- Seq(tree2, tree3)
yield tree match
  case Branch2(
    l as Branch2(ll as Leaf2(lli), lr as Leaf2(lri)),           // <3>
    r as Branch2(_,_)) => s"1: l=$l, r=$r, ll=$ll, lli=$lli, lr=$lr, lri=$lri"
  case _: Branch2[?] => "2: Other Branch2"
  case Branch3(
    l as Branch3,
    r as Branch3(rl as Leaf3(rli), rr as Branch3(_,_))) =>
      s"3: l=$l, r=$r, rl=$rl, rli=$rli, rr=$rr"
  case _:Branch3[?] => "4: Other Branch3"

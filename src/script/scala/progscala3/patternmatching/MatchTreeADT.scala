// src/script/scala/progscala3/patternmatching/MatchTreeADT.scala

enum Tree[T]:
  case Branch(left: Tree[T], right: Tree[T])
  case Leaf(elem: T)

import Tree._
val tree1 = Branch(
  Branch(Leaf(1), Leaf(2)),
  Branch(Leaf(3), Branch(Leaf(4), Leaf(5))))
val tree2 = Branch(Leaf(6), Leaf(7))

for t <- Seq(tree1, tree2, Leaf(8))
yield t match
  case Branch(
    l as Branch(_,_),
    r as Branch(rl as Leaf(rli), rr as Branch(_,_))) =>
      s"l=$l, r=$r, rl=$rl, rli=$rli, rr=$rr"
  case Branch(l, r) => s"Other Branch($l, $r)"
  case Leaf(x) => s"Other Leaf($x)"

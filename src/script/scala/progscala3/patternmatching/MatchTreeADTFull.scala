// src/script/scala/progscala3/patternmatching/MatchTreeADTFull.scala
import progscala3.patternmatching.{STree, SBranch, SLeaf, Tree}
import Tree.{Branch, Leaf}

val tree2 = SBranch(
  SBranch(
    SLeaf(1),
    SLeaf(2)),
  SBranch(
    SLeaf(3),
    SBranch(SLeaf(4),SLeaf(5))))

import Tree._
val tree3 = Branch(
  Branch(
    Leaf(1),
    Leaf(2)),
  Branch(
    Leaf(3),
    Branch(Leaf(4),Leaf(5))))

val result = for
  tree <- Seq(tree2, tree3)
yield tree match
  case SBranch(
    l as SBranch(ll as SLeaf(lli), lr as SLeaf(lri)),           // <3>
    r as SBranch(_,_)) => s"1: l=$l, r=$r, ll=$ll, lli=$lli, lr=$lr, lri=$lri"
  case _: SBranch[?] => "2: Other SBranch"
  case Branch(
    l as Branch,
    r as Branch(rl as Leaf(rli), rr as Branch(_,_))) =>
      s"3: l=$l, r=$r, rl=$rl, rli=$rli, rr=$rr"
  case _:Branch[?] => "4: Other Branch"

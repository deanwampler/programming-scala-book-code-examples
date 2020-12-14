// src/main/scala/progscala3/patternmatching/MatchTreeADTSealed.scala
package progscala3.patternmatching

sealed trait STree[T]               // "S" for "sealed"
case class SBranch[T](left: STree[T], right: STree[T]) extends STree[T]
case class SLeaf[T](elem: T) extends STree[T]

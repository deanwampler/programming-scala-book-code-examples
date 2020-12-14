// src/main/scala/progscala3/patternmatching/MatchTreeADTEnum.scala
package progscala3.patternmatching

enum Tree[T]:
  case Branch(left: Tree[T], right: Tree[T])
  case Leaf(elem: T)

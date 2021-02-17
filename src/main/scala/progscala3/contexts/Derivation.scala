// src/main/scala/progscala3/contexts/Derivation.scala

package progscala3.contexts
import scala.language.strictEquality

enum Tree[T] derives CanEqual:
  case Branch(left: Tree[T], right: Tree[T])
  case Leaf(elem: T)

@main def TryDerived() =
  import Tree.*
  val l1 = Leaf("l1")
  val l2 = Leaf(2)
  val b = Branch(l1,Branch(Leaf("b1"),Leaf("b2")))
  assert(l1 == l1)
  // assert(l1 != l2)   // Compilation error!
  assert(l1 != b)
  assert(b  == b)
  println(s"For String, String: ${summon[CanEqual[Tree[String],Tree[String]]]}")
  println(s"For Int, Int: ${summon[CanEqual[Tree[Int],Tree[Int]]]}")
  // Compilation error:
  // println(s"For String, Int: ${summon[CanEqual[Tree[String],Tree[Int]]]}")

// tag::revisited[]
// src/script/scala/progscala3/contexts/InfixTypeRevisited.scala
import scala.annotation.targetName

@targetName("TIEFighter") case class <+>[A,B](a: A, b: B)       // <1>
extension [A] (a: A) def <+>[B](b: B): A <+> B = new <+>(a, b)  // <2>

val ab1: Int <+> String = 1 <+> "one"                           // <3>
val ab2: Int <+> String = <+>(1, "one")                         // <4>
// end::revisited[]

// src/script/scala/progscala3/contexts/InfixType2.scala
import scala.annotation.alpha

@alpha("plusplus") case class ++[A,B](a: A, b: B)

// Valid alternative extension method syntax, Aug 2020.
// Will it still be valid in Scala 3 final??
@alpha("plusplus") def [A,B](a: A) ++(b: B): ++[A,B] = new ++(a, b)

val ab1: Int ++ String = 1 ++ "one"
val ab2: Int ++ String = ++(1, "one")

// src/script/scala/progscala3/implicits/InfixType2.scala
import scala.annotation.infix

@infix case class ++[A,B](a: A, b: B)

def [A,B](a: A) ++(b: B): ++[A,B] = new ++(a, b) // <1>

val ab1: Int ++ String = 1 ++ "one"              // <2>
val ab2: Int ++ String = ++(1, "one")            // <3>

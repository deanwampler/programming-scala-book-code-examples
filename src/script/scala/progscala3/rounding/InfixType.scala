// src/script/scala/progscala3/rounding/InfixType.scala
import scala.annotation.infix

@infix case class ++[A,B](a: A, b: B)            // <1>
val ab1: Int ++ String = 1 ++ "one"              // <2>
val ab2: Int ++ String = ++(1, "one")            // <3>

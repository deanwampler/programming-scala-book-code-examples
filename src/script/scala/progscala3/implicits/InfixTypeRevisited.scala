// src/script/scala/progscala3/rounding/InfixTypeRevisited.scala
import scala.annotation.infix

@infix case class ++[A,B](a: A, b: B)            // <1>
extension [A,B](a: A):
	def ++(b: B): A ++ B = ++(a, b)

val ab1: Int ++ String = 1 ++ "one"              // <2>
val ab2: Int ++ String = ++(1, "one")            // <3>

// src/script/scala/progscala3/rounding/InfixType.scala
import scala.annotation.{alpha, infix}

@alpha("BangBang") case class !![A,B](a: A, b: B)     // <1>
val ab1: Int !! String = 1 !! "one"                   // <2>
val ab2: Int !! String = !!(1, "one")                 // <3>

@infix case class bangbang[A,B](a: A, b: B)           // <4>
val ab1: Int bangbang String = 1 bangbang "one"
val ab2: Int bangbang String = bangbang(1, "one")

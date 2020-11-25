// src/script/scala/progscala3/rounding/InfixType.scala
import scala.annotation.targetName

@targetName("TIEFighter") case class <+>[A,B](a: A, b: B)  // <1>
val ab1: Int <+> String = 1 <+> "one"                      // <2>
val ab2: Int <+> String = <+>(1, "one")                    // <3>

infix case class tie[A,B](a: A, b: B)                      // <4>
val ab3: Int tie String = 1 tie "one"
val ab4: Int tie String = tie(1, "one")

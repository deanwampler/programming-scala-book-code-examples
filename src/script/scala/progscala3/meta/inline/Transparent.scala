// src/script/scala/progscala3/meta/inline/Transparent.scala

open class C1
class C2 extends C1

transparent inline def make(b: Boolean): C1 = if b then C1() else C2()

val c1: C1 = make(true)                 // <1>
val c2: C2 = make(false)                // <2>

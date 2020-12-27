// src/script/scala/progscala3/meta/inline/Transparent.scala

trait T
class C extends T

transparent inline def makeT(b: Boolean): T = if b then new T {} else new C

val t: T = makeT(true)                 // <1>
val c: C = makeT(false)                // <2>

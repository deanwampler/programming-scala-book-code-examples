// src/main/scala/progscala3/meta/reflection/JReflect.scala
package progscala3.meta.reflection

object JReflect:
  trait T[A]:
    val vT: A
    def mT = vT

  case class C[B](b: B) extends T[String]:
    val vT = "T"
    val vC = "C"
    def mC = vC
    class C2

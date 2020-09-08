// src/main/scala/progscala3/metaprogramming/Reflection.scala
package progscala3.metaprogramming

object Reflection:

  trait T[A]:
    val vT: A
    def mT = vT

  case class C[B](b: B) extends T[String]:
    val vT = "T"
    val vC = "C"

    def mC = vC

    class C2

  val clazz = classOf[C[Double]] // Scala method: classOf[C] ~ C.class

  // Helper method to convert an array to a useful string.
  def as(array: Array[?]): String = array.mkString("[", ", ", "]")

@main def TryReflection =
  import Reflection._
  println(s"clazz:                   ${clazz}")
  println(s"clazz.getSuperclass:     ${clazz.getSuperclass}")
  println(s"clazz.getTypeParameters: ${as(clazz.getTypeParameters)}")
  println(s"clazz.getClasses:        ${as(clazz.getClasses)}")
  println(s"clazz.getInterfaces:     ${as(clazz.getInterfaces)}")
  println(s"clazz.getName:           ${clazz.getName}")
  println(s"clazz.getModifiers:      ${clazz.getModifiers}")
  println(s"clazz.getConstructors:   ${as(clazz.getConstructors)}")
  println(s"clazz.getMethods:        ${as(clazz.getMethods)}")
  println(s"clazz.getFields:         ${as(clazz.getFields)}")
  println(s"clazz.getAnnotations:    ${as(clazz.getAnnotations)}")

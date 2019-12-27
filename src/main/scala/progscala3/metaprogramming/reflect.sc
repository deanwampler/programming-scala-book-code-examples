// src/main/scala/progscala3/metaprogramming/reflect.sc

trait T[A] {
  val vT: A
  def mT = vT
}

class C(foo: Int) extends T[String] {
  val vT = "T"
  val vC = "C"
  def mC = vC

  class C2
}

val c = new C(3)

val clazz = classOf[C]       // Scala method: classOf[C] ~ C.class
val clazz2 = c.getClass      // Method from java.lang.Object

// Helper method to convert an array to a useful string.
def as(array: Array[_]): String = array.mkString("[", ", ", "]")

println(s"clazz.getName:         ${clazz.getName}")
println(s"clazz.getMethods:      ${as(clazz.getMethods)}")
println(s"clazz.getConstructors: ${as(clazz.getConstructors)}")
println(s"clazz.getFields:       ${as(clazz.getFields)}")
println(s"clazz.getAnnotations:  ${as(clazz.getAnnotations)}")
println(s"clazz.getInterfaces:   ${as(clazz.getInterfaces)}")

// Compare the outputs of the following calls on clazz and clazz2:

println(s"clazz.getSuperclass:  ${clazz.getSuperclass}")
println(s"clazz2.getSuperclass: ${clazz2.getSuperclass}")

println(s"clazz.getTypeParameters:  ${as(clazz.getTypeParameters)}")
println(s"clazz2.getTypeParameters: ${as(clazz2.getTypeParameters)}")

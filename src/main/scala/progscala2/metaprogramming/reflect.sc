// src/main/scala/progscala2/metaprogramming/reflect.sc
import scala.language.existentials

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

val clazz = classOf[C]              // Scala method: classOf[C] ~ C.class
val clazz2 = c.getClass             // Method from java.lang.Object

// Methods from java.lang.Class<T>:
val name  = clazz.getName
val methods = clazz.getMethods
val ctors = clazz.getConstructors
val fields = clazz.getFields
val annos = clazz.getAnnotations
val interfaces = clazz.getInterfaces
val superClass = clazz.getSuperclass
val typeParams = clazz.getTypeParameters

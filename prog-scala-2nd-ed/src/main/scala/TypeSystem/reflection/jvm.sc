// code-examples/TypeSystem/reflection/jvm-script.scala

trait T[A] {
  val vT: A
  def mT = vT
}

class C extends T[String] {
  val vT = "T"
  val vC = "C"
  def mC = vC
  
  class C2
  trait T2
}

val c = new C
val clazz = c.getClass              // method from java.lang.Object
val clazz2 = classOf[C]             // Scala method: classOf[C] ~ C.class
val methods = clazz.getMethods      // method from java.lang.Class<T>
val ctors = clazz.getConstructors   // ...
val fields = clazz.getFields
val annos = clazz.getAnnotations 
val name  = clazz.getName
val parentInterfaces = clazz.getInterfaces
val superClass = clazz.getSuperclass
val typeParams = clazz.getTypeParameters

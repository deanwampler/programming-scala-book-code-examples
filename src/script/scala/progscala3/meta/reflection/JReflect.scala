// src/script/scala/progscala3/meta/reflection/JReflect.scala
import progscala3.meta.reflection.JReflect

def as(array: Array[?]): String = array.mkString("[", ", ", "]") // <1>

val clazz = classOf[JReflect.C[Double]] // Scala method: classOf[C] ~ C.class

clazz.getName
clazz.getModifiers
val sup = clazz.getSuperclass
as(clazz.getTypeParameters)
as(clazz.getClasses)
as(clazz.getInterfaces)
as(clazz.getConstructors)
as(clazz.getMethods)
as(clazz.getFields)
as(clazz.getAnnotations)

sup.getName
sup.getModifiers
as(sup.getTypeParameters)
as(sup.getClasses)
as(sup.getInterfaces)
as(sup.getConstructors)
as(sup.getMethods)
as(sup.getFields)
as(sup.getAnnotations)

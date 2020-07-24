// src/main/scala/progscala3/typesystem/intersectionunion/Linearization.scala
package progscala3.typesystem.intersectionunion

trait M {
	def m(s: String): String = s
}

trait T1 extends M {
	override def m(s: String): String = s"[ ${super.m(s)} ]"
}

trait T2 extends M {
	override def m(s: String): String = s"( ${super.m(s)} )"
}

class C extends M {
	override def m(s: String): String = s"{ ${super.m(s)} }"
}

// src/main/scala/TypeSystem/typepaths/path-expressions.scala

package typesystem.typepaths

class C1 {
	var x = "1"
	def setX1(x:String) = this.x = x
	def setX2(x:String) = C1.this.x = x
}

trait T1 {
  class C
  val c1 = new C
  val c2 = new this.C
}

class C2 extends C1
class C3 extends C2 {
	def setX3(x:String) = super.setX1(x)
	def setX4(x:String) = C3.super.setX1(x)
	def setX5(x:String) = C3.super[C2].setX1(x)
}

class C4 {
  class C5
}
class C6 extends C4 {
  val c5a = new C5
  val c5b = new super.C5
}

package P1 {
	object O1 {
		object O2 {
			val name = "name"
		}
	}
} 
class C7 {
	val name = P1.O1.O2.name
}

object O3 {
  object O4 {
    type t = java.io.File
    class C
    trait T
  }
  class C2 {
    type t = Int
  }
}
class C8 {
  type t1 = O3.O4.t
  type t2 = O3.O4.C
  type t3 = O3.O4.T
//  type t4 = O3.C2.t   // ERROR: C2 is not a it ("value") O3
}

// src/main/scala/progscala2/typesystem/typepaths/path-expressions.scala
package progscala2.typesystem.typepaths

class C1 {
  var x = "1"
  def setX1(x:String): Unit = this.x = x
  def setX2(x:String): Unit = C1.this.x = x
}

trait T1 {
  class C
  val c1: C = new C
  val c2: C = new this.C
}

trait X {
  def setXX(x:String): Unit = {} // Do Nothing!
}
class C2 extends C1
class C3 extends C2 with X {
  def setX3(x:String): Unit = super.setX1(x)
  def setX4(x:String): Unit = C3.super.setX1(x)
  def setX5(x:String): Unit = C3.super[C2].setX1(x)
  def setX6(x:String): Unit = C3.super[X].setXX(x)
  // def setX7(x:String): Unit = C3.super[C1].setX1(x)    // ERROR
  // def setX8(x:String): Unit = C3.super.super.setX1(x)  // ERROR
}

class C4 {
  class C5
}
class C6 extends C4 {
  val c5a: C5 = new C5
  val c5b: C5 = new super.C5
}

package P1 {
  object O1 {
    object O2 {
      val name = "name"
    }
    class C1 {
      val name = "name"
    }
  }
}
class C7 {
  val  name1 = P1.O1.O2.name      // Okay  - a reference to a field
  type C1    = P1.O1.C1           // Okay  - a reference to a "leaf" class
  val  c1    = new P1.O1.C1       // Okay  - same reason
  // val name2 = P1.O1.C1.name    // ERROR - P1.O1.C1 isn't stable.
}
// src/test/scala/progscala3/objectsystem/linearization/LinearizationSuite.scala
// Essentially the same as src/script/.../Linearization.scala
package progscala3.contexts

import munit.*

class LinearizationSuite extends FunSuite:

  trait Base:
    var str = "Base"           // Used to track construction precedence
    def m(): String = "Base"   // Used to track method invocation precedence

  trait T1 extends Base:
    str = str + " T1"
    override def m(): String = { "T1 " +  super.m() }

  trait T2 extends Base:
    str = str + " T2"
    override def m(): String = { "T2 " +  super.m() }

  trait T3 extends Base:
    str = str + " T3"
    override def m(): String = { "T3 " +  super.m() }

  class C2 extends T2:
    str = str + " C2"
    override def m(): String = { "C2 " + super.m() }

  class C3A extends C2 with T1 with T2 with T3:
    str = str + " C3A"
    override def m(): String = { "C3A " + super.m() }

  class C3B extends C2 with T3 with T2 with T1:
    str = str + " C3B"
    override def m(): String = { "C3B " + super.m() }

  val c3a = new C3A
  val c3b = new C3B
  val c3c = new C2 with T1 with T2 with T3
  val c3d = new C2 with T3 with T2 with T1

  test("Linearization of construction is left to right") {
    assert(c3a.str == "Base T2 C2 T1 T3 C3A")
    assert(c3b.str == "Base T2 C2 T3 T1 C3B")
    assert(c3c.str == "Base T2 C2 T1 T3")
    assert(c3d.str == "Base T2 C2 T3 T1")
  }

  test("Linearization of method invocation is right to left") {
    assert(c3a.m()  == "C3A T3 T1 C2 T2 Base")
    assert(c3b.m()  == "C3B T1 T3 C2 T2 Base")
    assert(c3c.m()  == "T3 T1 C2 T2 Base")
    assert(c3d.m()  == "T1 T3 C2 T2 Base")
  }

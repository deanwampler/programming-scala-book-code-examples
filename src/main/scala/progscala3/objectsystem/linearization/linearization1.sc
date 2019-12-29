// src/main/scala/progscala3/objectsystem/linearization/linearization1.sc

class C1 {
  def m(): String = "C1 "
}

trait T1 extends C1 {
  override def m(): String = { "T1 " + super.m() }
}

trait T2 extends C1 {
  override def m(): String = { "T2 " + super.m() }
}

trait T3 extends C1 {
  override def m(): String = { "T3 " + super.m() }
}

class C2 extends T1 with T2 with T3 {
  override def m(): String = { "C2 " + super.m() }
}

val c1 = new C1
assert(c1.m() == "C1 ")
val c2 = new C2
assert(c2.m() == "C2 T3 T2 T1 C1 ")

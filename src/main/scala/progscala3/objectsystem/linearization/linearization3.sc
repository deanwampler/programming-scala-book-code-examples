// src/main/scala/progscala3/objectsystem/linearization/linearization3.sc

class C1 {
  def m(previous: String): String = s"C1($previous)"
}

trait T1 extends C1 {
  override def m(p: String): String = { super.m(s"T1($p)") }
}

trait T2 extends C1 {
  override def m(p: String): String = { super.m(s"T2($p)") }
}

trait T3 extends C1 {
  override def m(p: String): String = { super.m(s"T3($p)") }
}

class C2 extends T1 with T2 with T3 {
  override def m(p: String): String = { super.m(s"C2($p)") }
}

val c2 = new C2
assert(c2.m("_") == "C1(T1(T2(T3(C2(_)))))")
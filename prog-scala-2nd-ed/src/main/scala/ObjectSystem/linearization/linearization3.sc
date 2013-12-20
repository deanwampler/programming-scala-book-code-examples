// code-examples/ObjectSystem/linearization/linearization3-script.scala

class C1 {
  def m(previous: String) = List("C1("+previous+")")
}

trait T1 extends C1 {
  override def m(p: String) = { "T1" :: super.m("T1") }
}

trait T2 extends C1 {
  override def m(p: String) = { "T2" :: super.m("T2") }
}

trait T3 extends C1 {
  override def m(p: String) = { "T3" :: super.m("T3") }
}

class C2 extends T1 with T2 with T3 {
  override def m(p: String) = { "C2" :: super.m("C2") }
}

val c2 = new C2
println(c2.m(""))

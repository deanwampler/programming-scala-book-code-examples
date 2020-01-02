// src/main/scala/progscala3/objectsystem/linearization/linearization4.sc

class C1 {
  def m(): String = "C1 "
}

trait T1 extends C1 {
  override def m(): String = { "T1 " +  super.m() }
}

trait T2 extends C1 {
  override def m(): String = { "T2 " +  super.m() }
}

trait T3 extends C1 {
  override def m(): String = { "T3 " +  super.m() }
}

class C2A extends T2 {
  override def m(): String = { "C2A " + super.m() }
}

class C2 extends C2A with T1 with T2 with T3 {
  override def m(): String = { "C2 " + super.m() }
}

def lin(obj: C1, name: String): String = 
  s"$name: ${obj.m()} AnyRef Any"
    
assert(lin(new C2, "C2 ")    == "C2 : C2 T3 T1 C2A T2 C1  AnyRef Any")
assert(lin(new T3 {}, "T3 ") == "T3 : T3 C1  AnyRef Any")
assert(lin(new T2 {}, "T2 ") == "T2 : T2 C1  AnyRef Any")
assert(lin(new T1 {}, "T1 ") == "T1 : T1 C1  AnyRef Any")
assert(lin(new C2A, "C2A")   == "C2A: C2A T2 C1  AnyRef Any")
assert(lin(new C1, "C1 ")    == "C1 : C1  AnyRef Any")

// src/main/scala/progscala2/objectsystem/linearization/linearization4.sc

class C1 {
  def m = print("C1 ")
}

trait T1 extends C1 {
  override def m = { print("T1 "); super.m }
}

trait T2 extends C1 {
  override def m = { print("T2 "); super.m }
}

trait T3 extends C1 {
  override def m = { print("T3 "); super.m }
}

class C2A extends T2 {
  override def m = { print("C2A " ); super.m }
}

class C2 extends C2A with T1 with T2 with T3 {
  override def m = { print("C2 "); super.m }
}

def calcLinearization(obj: C1, name: String) = {
  print(s"$name: ")
  obj.m
  print("AnyRef ")
  println("Any")
}
    
calcLinearization(new C2, "C2 ")
println("")
calcLinearization(new T3 {}, "T3 ")
calcLinearization(new T2 {}, "T2 ")
calcLinearization(new T1 {}, "T1 ")
calcLinearization(new C2A, "C2A")
calcLinearization(new C1, "C1 ")

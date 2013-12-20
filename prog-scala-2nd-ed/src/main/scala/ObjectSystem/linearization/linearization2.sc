// code-examples/ObjectSystem/linearization/linearization2-script.scala

var clist = List[String]()

class C1 {
  clist ::= "C1"
}

trait T1 extends C1 {
  clist ::= "T1"
}

trait T2 extends C1 {
  clist ::= "T2"
}

trait T3 extends C1 {
  clist ::= "T3"
}

class C2 extends T1 with T2 with T3 {
  clist ::= "C2"
}

val c2 = new C2
println(clist.reverse)

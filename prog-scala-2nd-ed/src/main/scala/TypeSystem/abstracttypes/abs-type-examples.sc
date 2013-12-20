// code-examples/TypeSystem/abstracttypes/abs-type-examples-script.scala

trait exampleTrait {
  type t1               // Unconstrained
  type t2 >: t3 <: t1   // t2 must be a supertype of t3 and a subtype of t1
  type t3 <: t1         // t3 must be a subtype of t1
  type t4               // Unconstrained
  type t5 = List[t4]    // List of t4, whatever t4 will eventually be...
  
  val v1: t1            // Can't initialize until t1 defined.
  val v3: t3            // etc.
  val v2: t2            // ...
  val v4: t4            // ...
  val v5: t5            // ...
}

trait T1 { val name1: String }
trait T2 extends T1 { val name2: String }
class C(val name1: String, val name2: String) extends T2

object example extends exampleTrait {
  type t1 = T1
  type t2 = T2
  type t3 = C
  type t4 = Int
  
  val v1 = new T1 { val name1 = "T1"}
  val v3 = new C("C1", "C2")
  val v2 = new T2 { val name1 = "T1"; val name2 = "T2" }
  val v4 = 10
  val v5 = List(1,2,3,4,5)
}

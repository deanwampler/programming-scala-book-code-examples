// code-examples/TypeSystem/valuetypes/type-projection-script.scala

trait T {      
  type t <: AnyRef
}
class C1 extends T {      
  type t = String
}
class C2 extends C1

val ic1: C1#t = "C1"
val ic2: C2#t = "C2"
println(ic1)
println(ic2)


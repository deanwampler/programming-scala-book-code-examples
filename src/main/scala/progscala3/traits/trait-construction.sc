// src/main/scala/progscala3/traits/trait-construction.sc

trait T1 {
  // The following would cause: "uninitialized value"
  // println(s"  in T1: x = $x")
  val x=1
  println(s"  in T1: x = $x")
}

trait T2 {
  // The following would cause: "uninitialized value"
  // println(s"  in T2: y = $y")
  val y="T2"
  println(s"  in T2: y = $y")
}

class Base12 {
  // The following would cause: "warning: Reference to uninitialized value b"
  // println(s"  in Base12: b = $b") 
  val b="Base12"
  println(s"  in Base12: b = $b")
}

class C12 extends Base12 with T1 with T2 {
  // The following would cause: "warning: Reference to uninitialized value c"
  // println(s"  in C12: c = $c")
  val c="C12"
  println(s"  in C12: c = $c")
}

println(s"Creating C12:")
new C12
println(s"After Creating C12")


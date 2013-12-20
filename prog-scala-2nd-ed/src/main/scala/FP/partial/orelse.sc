// code-examples/FP/partial/orelse-script.scala

val truthier: PartialFunction[Boolean, String] = { case true => "truthful" }
val fallback: PartialFunction[Boolean, String] = { case x => "sketchy" }
val tester = truthier orElse fallback

println(tester(1 == 1))
println(tester(2 + 2 == 5))
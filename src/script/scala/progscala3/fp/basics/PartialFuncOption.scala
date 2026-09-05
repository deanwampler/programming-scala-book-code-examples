// src/script/scala/progscala3/fp/basics/PartialFuncOption.scala
val finicky: PartialFunction[String,String] =
  case "finicky" => "FINICKY"

println("This script throws MatchErrors!")
finicky("finicky")
finicky("other")                       // MatchError

val finickyOption = finicky.lift

finickyOption("finicky")
finickyOption("other")

val finicky2 = Function.unlift(finickyOption)
finicky2("finicky")
finicky2("other")                      // MatchError

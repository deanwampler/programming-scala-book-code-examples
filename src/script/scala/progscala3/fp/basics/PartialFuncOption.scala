// src/script/scala/progscala3/fp/basics/PartialFuncOption.scala
val finicky: PartialFunction[String,String] =
  case "finicky" => "FINICKY"

finicky("finicky")
finicky("other")                       // MatchError

val finickyOption = finicky.lift

finickyOption("finicky")
finickyOption("other")

val finicky2 = Function.unlift(finickyOption)
finicky2("finicky")
finicky2("other")                      // MatchError

// src/main/scala/FP/pfunk/lifted-func.sc

val finicky: PartialFunction[String,String] = {
  case "finicky" => "FINICKY"
}

val finickyOption = finicky.lift

finicky("finicky")
finicky("other")

finickyOption("finicky")
finickyOption("other")

val finicky2 = Function.unlift(finickyOption)

finicky2("finicky")
finicky2("other")

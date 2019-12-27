// src/main/scala/progscala3/fp/curry/lifted-func.sc

val finicky: PartialFunction[String,String] = {
  case "finicky" => "FINICKY"
}

val finickyOption = finicky.lift

finicky("finicky")
try {
  finicky("other")
} catch {
  case e: scala.MatchError => e
}

finickyOption("finicky")
finickyOption("other")

val finicky2 = Function.unlift(finickyOption)

finicky2("finicky")
try {
  finicky2("other")
} catch {
  case e: scala.MatchError => e
}

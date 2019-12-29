// src/main/scala/progscala3/fp/curry/lifted-func.sc

val finicky: PartialFunction[String,String] = {
  case "finicky" => "FINICKY"
}

val finickyOption = finicky.lift

assert(finicky("finicky") == "FINICKY")

try {
  finicky("other")
  assert(false, "Should not be here!")
} catch {
  case e: scala.MatchError => assert(e.getMessage.contains("other"))
}

assert(finickyOption("finicky") == Some("FINICKY"))
assert(finickyOption("other")   == None)

val finicky2 = Function.unlift(finickyOption)

assert(finicky2("finicky") == "FINICKY")

try {
  finicky2("other")
  assert(false, "Should not be here!")
} catch {
  case e: scala.MatchError => assert(e.getMessage.contains("other"))
}

// src/script/scala/progscala3/fp/curry/Lifting.scala

val finicky: PartialFunction[String,String] =
  case "finicky" => "FINICKY"

val fin = finicky("finicky")

try
  finicky("other")
catch
  case e: scala.MatchError => println(e.getMessage)

val finickyOption = finicky.lift

val fo1 = finickyOption("finicky")
val fo2 = finickyOption("other")

val finicky2 = Function.unlift(finickyOption)

val fin2 = finicky2("finicky")

try
  finicky2("other")
catch
  case e: scala.MatchError => println(e.getMessage)

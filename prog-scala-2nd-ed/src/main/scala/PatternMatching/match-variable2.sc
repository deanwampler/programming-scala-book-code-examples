// src/main/scala/PatternMatching/match-variable2.sc

for {
  x <- List (1, 2, 2.7, 3.14159, "one", "two", 'four)
} {
  val str = x match {
    case 1 => "int 1"
    case _: Int => "other int: "+x
    case 2.7 => "double 2.7"
    case _: Double => "other double: "+x
    case "one" => "string one"
    case _: String => "other string: "+x
    case _ => "unexpected value: " + x
  }
  println(str)
}


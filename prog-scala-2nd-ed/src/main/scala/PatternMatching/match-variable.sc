// src/main/scala/PatternMatching/match-variable.sc

for {
  x <- List (1, 2, 2.7, 3.14159, "one", "two", 'four)
} {
  val str = x match {
    case 1 => "int 1"
    case i: Int => "other int: "+i
    case 2.7 => "double 2.7"
    case d: Double => "other double: "+d
    case "one" => "string one"
    case s: String => "other string: "+s
    case unexpected => "unexpected value: " + unexpected
  }
  println(str)
}


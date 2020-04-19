// src/script/scala/progscala3/patternmatching/MatchVariable.scala

val seq = Seq(1, "one", 2, 3.14, "four", 5.5F, (6, 7))           // <1>
val result = seq map {
  case 1                   => "int 1"                            // <2>
  case i: Int              => "other int: "+i
  case d: (Double | Float) => "a double or float: "+d            // <3>
  case "one"               => "string one"                       // <4>
  case s: String           => "other string: "+s
  case unexpected          => "unexpected value: " + unexpected  // <5>
}
assert(result == Seq(
  "int 1", "string one", "other int: 2",
  "a double or float: 3.14", "other string: four",
  "a double or float: 5.5", "unexpected value: (6,7)"))

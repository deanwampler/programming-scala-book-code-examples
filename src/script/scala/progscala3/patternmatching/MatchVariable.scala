// src/script/scala/progscala3/patternmatching/MatchVariable.scala

val seq = Seq(1, 2, 3.14, 5.5F, "one", "four", true, (6, 7))    // <1>
val result = seq.map {
  case 1                   => "int 1"                           // <2>
  case i: Int              => s"other int: $i"
  case d: (Double | Float) => s"a double or float: $d"          // <3>
  case "one"               => "string one"                      // <4>
  case s: String           => s"other string: $s"
  case (x, y)              => s"tuple: ($x, $y)"                // <5>
  case unexpected          => s"unexpected value: $unexpected"  // <6>
}
assert(result == Seq(
  "int 1", "other int: 2",
  "a double or float: 3.14", "a double or float: 5.5",
  "string one", "other string: four",
  "unexpected value: true",
  "tuple: (6, 7)"))

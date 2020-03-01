// src/main/scala/progscala3/patternmatching/match-variable.sc

val result = Seq(1, "one", 2, 3.14, "four", Symbol("five")) map {  // <1>
  case 1          => "int 1"                                       // <3>
  case i: Int     => "other int: "+i                               // <4>
  case d: Double  => "a double: "+d                                // <5>
  case "one"      => "string one"                                  // <6>
  case s: String  => "other string: "+s                            // <7>
  case unexpected => "unexpected value: " + unexpected             // <8>
}
assert(result == Seq(
  "int 1", "string one", "other int: 2", 
  "a double: 3.14", "other string: four", "unexpected value: 'five"))

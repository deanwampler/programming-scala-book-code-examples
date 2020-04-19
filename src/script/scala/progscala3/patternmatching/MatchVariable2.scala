// src/script/scala/progscala3/patternmatching/MatchVariable2.scala

val seq = Seq(1, "one", 2, 3.14, "four", 5.5F, (6, 7))
val result = seq map {
  item => item match {
	  case 1                   => "int 1"
	  case i: Int              => "other int: "+i
	  case d: (Double | Float) => "a double or float: "+d
	  case "one"               => "string one"
	  case s: String           => "other string: "+s
	  case unexpected          => "unexpected value: " + unexpected
	}
}

assert(result == Seq(
  "int 1", "string one", "other int: 2",
  "a double or float: 3.14", "other string: four",
  "a double or float: 5.5", "unexpected value: (6,7)"))

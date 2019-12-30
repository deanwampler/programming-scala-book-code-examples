// src/main/scala/progscala3/patternmatching/match-variable3.sc

val result = Seq(1, "one", 2, 3.14, "four", Symbol("five")) map {
  str => str match {                                      // <1>
    case _: Int | _: Double => "a number: " + str
    case "one"              => "string one"
    case _: String          => "other string: " + str
    case _                  => "unexpected value: " + str
  }
}
assert(result == Seq(
  "a number: 1", "string one", "a number: 2", 
  "a number: 3.14", "other string: four", "unexpected value: 'five"))

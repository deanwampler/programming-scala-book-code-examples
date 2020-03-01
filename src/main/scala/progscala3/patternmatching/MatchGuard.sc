// src/main/scala/progscala3/patternmatching/match-guard.sc

val results = Seq(1,2,3,4) map {
  case e if e%2 == 0 => s"even: $e"                          // <1>
  case o             => s"odd:  $o"                          // <2>
}
assert(results == Seq("odd:  1", "even: 2", "odd:  3", "even: 4"))

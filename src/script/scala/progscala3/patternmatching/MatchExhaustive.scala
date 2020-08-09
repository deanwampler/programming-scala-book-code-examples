// src/script/scala/progscala3/patternmatching/MatchVariable.scala

val seq3 = Seq(Some(1), None, Some(2), None)
val result3 = seq3 map {
  case Some(i)  => "int 1"
}

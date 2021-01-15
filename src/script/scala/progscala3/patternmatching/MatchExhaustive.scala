// src/script/scala/progscala3/patternmatching/MatchExhaustive.scala

val seq3 = Seq(Some(1), None, Some(2), None)
val result3 = seq3.map {
  case Some(i)  => s"int $i"
}

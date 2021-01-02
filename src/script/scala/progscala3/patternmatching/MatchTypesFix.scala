// src/script/scala/progscala3/patternmatching/MatchTypesFix.scala

def doSeqMatch[T <: Matchable](seq: Seq[T]): String = seq match
  case Nil => "Nothing"
  case head +: _ => head match
    case _ : Double => "Double"
    case _ : String => "String"
    case _ => "Unmatched seq element"

val results = Seq(Seq(5.5,5.6,5.7), Seq("a", "b"), Nil) map {
  case seq: Seq[?] => (s"seq ${doSeqMatch(seq)}", seq)          // <1>
}

assert(results == Seq(
  ("seq Double",  Seq(5.5, 5.6, 5.7)),
  ("seq String",  Seq("a", "b")),
  ("seq Nothing", Seq())))

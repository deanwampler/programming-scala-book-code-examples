// src/main/scala/progscala3/patternmatching/match-types-fix.sc

def doSeqMatch[T](seq: Seq[T]): String = seq match {
  case Nil => "Nothing"
  case head +: _ => head match {
    case _ : Double => "Double"
    case _ : String => "String"
    case _ => "Unmatched seq element"
  }
}

val results = Seq(List(5.5,5.6,5.7), List("a", "b"), Nil) map {
  case seq: Seq[_] => (s"seq ${doSeqMatch(seq)}", seq)
  case x           => ("unknown!", x)
}
assert(results == Seq(
  ("seq Double",  List(5.5, 5.6, 5.7)), 
  ("seq String",  List("a", "b")), 
  ("seq Nothing", List())))

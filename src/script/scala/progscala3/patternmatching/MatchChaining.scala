// src/script/scala/progscala3/patternmatching/MatchChaining.scala

for opt <- Seq(Some(1), None)
yield opt match {  // Braces for clarity, but not required! Try removing them.
  case None => ""
  case Some(i) => i.toString
} match {        // will match on the String from the previous match
  case "" => false
  case _ => true
}

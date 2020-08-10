// src/script/scala/progscala3/patternmatching/MatchChaining.scala

for opt <- Seq(Some(1), None)
yield opt match {
	case None => ""
	case Some(i) => i.toString
} match {
	case "" => false
	case _ => true
}

// src/script/scala/progscala3/patternmatching/MatchRepeatedParams.scala

def matchThree(seq: Seq[Int]) = seq match
  case Seq(h1, h2, rest: _*) =>
    println(s"head 1 = $h1, head 2 = $h2, the rest = $rest")
  case _ => println("Other! $seq")

matchThree(Seq(1,2,3,4))
matchThree(Seq(1,2,3))
matchThree(Seq(1,2))
matchThree(Seq(1))

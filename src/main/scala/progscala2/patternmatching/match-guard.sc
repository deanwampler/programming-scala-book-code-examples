// src/main/scala/progscala2/patternmatching/match-guard.sc

for (i <- Seq(1,2,3,4)) {
  i match {
    case _ if i%2 == 0 => println(s"even: $i")                       // <1>
    case _             => println(s"odd:  $i")                       // <2>
  }
}

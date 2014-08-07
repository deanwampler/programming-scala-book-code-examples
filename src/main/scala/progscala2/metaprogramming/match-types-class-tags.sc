// src/main/scala/progscala2/metaprogramming/match-types-class-tags.sc

import scala.reflect.ClassTag

def doSeqMatch[T : ClassTag](seq: Seq[T]): String = seq match {
  case Nil => "Nothing"
  case head +: _ => implicitly(seq.head).getClass.toString
}

for {
  x <- List(List(5.5,5.6,5.7), List("a", "b"), Nil) 
} yield (x match {
  case seq: Seq[_] => (s"seq ${doSeqMatch(seq)}", seq)
  case _           => ("unknown!", x)
})

// src/main/scala/Rounding/match-types.sc

for {
  x <- List("one", 2, (3,"three"), 4.4,
            List(5.5,5.6,5.7), List("a", "b")) 
} yield (x match {
  case s: String => ("string", s)
  case i: Int => ("int", i)
  case d: Double => ("double", d)
  case t: (Int, String) => (("int", "string"), (t._1, t._2))
  case l: List[Double] => ("list double", l)
  case _ => ("unknown!", x)
})

// src/main/scala/Rounding/match-types2.sc

for {
  x <- List("one", 2, (3,"three"), (3.3, 'three), 4.4,
            List(5.5,5.6,5.7), List("a", "b")) 
} yield (x match {
  case s: String => ("string", s)
  case i: Int => ("int", i)
  case d: Double => ("double", d)
  case t @ (x, y) => x match {
    case _: Int => y match {
      case _: String =>  (("int", "string"), (x, y))
      case _ => ("unknown tuple", t)
    }
    case _ => ("unknown tuple", t)
  }
  case l: List[_] => ("list", l)
  case _ => ("unknown!", x)
})

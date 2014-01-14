// src/main/scala/Rounding/match-surprise.sc

val y = 100
for {
  x <- List (99, 100, 101)
} {
  val str = x match {
    case y => "found y!"
    case i: Int => "int: "+i
  }
  println(str)
}


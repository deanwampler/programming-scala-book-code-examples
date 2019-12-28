// src/main/scala/progscala3/fp/datastructs/vector.sc

val vect1 = Vector("Programming", "Scala")
val vect2 = "People" +: "should" +: "read" +: vect1
assert(vect2 == 
  List("People", "should", "read", "Programming", "Scala"))

val vect3 = "Programming" +: "Scala" +: Vector.empty[String]
val vect4 = "People" +: "should" +: "read" +: Vector.empty[String]
val vect5 = vect4 ++ vect3
assert(vect5 == 
  List("People", "should", "read", "Programming", "Scala"))

// src/main/scala/progscala2/fp/datastructs/vector.sc

val vect1 = Vector("Programming", "Scala")
val vect2 = "People" +: "should" +: "read" +: vect1
println(vect2)

val vect3 = "Programming" +: "Scala" +: Vector.empty[String]
val vect4 = "People" +: "should" +: "read" +: Vector.empty[String]
val vect5 = vect4 ++ vect3
println(vect5)

// src/script/scala/progscala3/fp/datastructs/Vector.scala

val vect1 = Vector("Programming", "Scala")
val vect2 = "People" +: "should" +: "read" +: Vector.empty // <1>

vect2.head
vect2.tail

val seq1 = Seq("Programming", "Scala")
val vect3 = seq1.toVector                                  // <2>

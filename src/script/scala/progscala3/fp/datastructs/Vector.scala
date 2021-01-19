// src/script/scala/progscala3/fp/datastructs/Vector.scala

val vect1 = Vector("Programming", "Scala")
val vect2 = "People" +: "should" +: "read" +: Vector.empty      // <1>
val vect3 = "People" +: "should" +: "read" +: vect1
val vect4 = Vector.empty :+ "People" :+ "should" :+ "read"      // <2>

vect3.head
vect3.tail

val seq1 = Seq("Programming", "Scala")
val vect5 = seq1.toVector                                       // <3>

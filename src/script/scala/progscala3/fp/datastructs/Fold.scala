// src/script/scala/progscala3/fp/datastructs/Fold.scala

val vector = Vector(1, 2, 3, 4, 5, 6)

val vector2 = vector.foldLeft(Vector.empty[String]) {      // <1>
  (vector, x) => vector :+ ("[" + x + "]")
}

val vector3 = vector.foldLeft(Vector.empty[Int]) {         // <2>
  (vector, x) => if x % 2 == 0 then vector else vector :+ x
}

val vector4a = vector.foldLeft(Vector.empty[Seq[Int]]) {   // <3>
  (vector, x) =>  vector :+ (1 to x)
}
val vector4 = vector4a.flatten                             // <4>

val vector2b = vector.foldRight(Vector.empty[String]) {    // <5>
  (x, vector) => ("[" + x + "]") +: vector
}

// src/main/scala/progscala3/fp/datastructs/fold-map.sc

val vector2 = Vector(1, 2, 3, 4, 5, 6).foldRight(Vector.empty[String]) {
  (x, vector) => ("[" + x + "]") +: vector
}

assert(vector2 == Vector("[1]", "[2]", "[3]", "[4]", "[5]", "[6]"))

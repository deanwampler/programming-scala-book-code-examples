// src/script/scala/progscala3/traits/UnionTypes.scala

def process(i: Int): Int | String =
  if (i < 0) then "Negative integer!" else i

val result1a: Int | String = process(-1)
val result2a: Int | String = process(1)
val result1b = process(-1)
val result2b = process(1)

Seq(process(-1), process(1)).map {
  case i: Int => "integer"
  case s: String => "string"
}

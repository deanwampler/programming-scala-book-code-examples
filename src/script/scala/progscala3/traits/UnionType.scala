// src/script/scala/progscala3/traits/UnionType.scala

def process(i: Int): Int | String =
  if (i < 0) then "Negative integer!" else i

process(-1)
process(1)

Seq(process(-1), process(1)).map {
  case i: Int => "integer"
  case s: String => "string"
}

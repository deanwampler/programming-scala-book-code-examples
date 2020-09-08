// src/script/scala/progscala3/rounding/IfTyped.scala

val seq = (0 until 6) map { n =>
  if n%2 == 0 then Some(n.toString)
  else None
}

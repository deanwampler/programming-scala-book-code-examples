// src/script/scala/progscala3/rounding/IfTyped.scala

val seq = (0 until 6) map { n =>
  if n%2 == 0 then s"$n is even"
  else if n%3 == 0 then 1.0*n
  else n
}

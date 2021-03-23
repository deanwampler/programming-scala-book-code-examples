// tag::one[]
// src/script/scala/progscala3/rounding/If.scala

(0 until 6).map { n =>
  if n%2 == 0 then
    s"$n is even"
  else if n%3 == 0 then
    s"$n is divisible by 3"
  else
    n.toString
}
// end::one[]

// tag::two[]
(0 until 6).map { n =>
  if n%2 == 0 then s"$n is even"
  else if n%3 == 0 then s"$n is divisible by 3"
  else n.toString
}
// end::two[]

// src/script/scala/progscala3/rounding/If2.scala

(0 until 6) foreach { n =>
  if n%2 == 0 then println(s"$n is even")
  else if n%3 == 0 then println(s"$n is divisible by 3")
  else println(n)
}

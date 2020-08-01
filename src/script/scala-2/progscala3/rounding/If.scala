// src/script/scala-2/progscala3/rounding/If.scala

(0 until 6) foreach { n =>
  if (n%2 == 0) {
    println(s"$n is even")
  } else if (n%3 == 0) {
    println(s"$n is divisible by 3")
  } else {
    println(n)
  }
}

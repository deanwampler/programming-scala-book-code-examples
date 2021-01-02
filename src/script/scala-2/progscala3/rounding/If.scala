// src/script/scala-2/progscala3/rounding/If.scala

(0 until 6).map { n =>
  if (n%2 == 0) {
    s"$n is even"
  } else if (n%3 == 0) {
    s"$n is divisible by 3"
  } else {
    n
  }
}

(0 until 6).map { n =>
  if (n%2 == 0) s"$n is even"
  else if (n%3 == 0) s"$n is divisible by 3"
  else n
}

// src/main/scala/progscala3/rounding/do-while.sc

var count = 0

do {
  count += 1
  println(count)
} while (count < 10)
assert(count == 10)

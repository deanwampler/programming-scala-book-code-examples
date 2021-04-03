// src/script/scala/progscala3/hierarchy/Asserts.scala
val n = 5
assert(n > 0, s"Must assign a positive number. $n given.")

def factorial(n: Long): Long = {
  require(n >= 0, s"factorial($n): Must pass a positive number!")
  if n == 1 then n
  else n*factorial(n-1)
} ensuring(_ > 0, "BUG!!")

factorial(-1)
factorial(5)

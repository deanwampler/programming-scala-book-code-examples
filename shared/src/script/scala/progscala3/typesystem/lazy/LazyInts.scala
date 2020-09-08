// src/script/scala/progscala3/typesystem/lazy/LazyInts.scala

def from(n: Int): LazyList[Int] = LazyList.cons(n, from(n+1))

lazy val ints = from(0)
lazy val odds = ints.filter(_ % 2 == 1)
lazy val evens = ints.filter(_ % 2 == 0)

assert(odds.take(10).force  == LazyList(1, 3, 5, 7, 9, 11, 13, 15, 17, 19))
assert(evens.take(10).force == LazyList(0, 2, 4, 6, 8, 10, 12, 14, 16, 18))

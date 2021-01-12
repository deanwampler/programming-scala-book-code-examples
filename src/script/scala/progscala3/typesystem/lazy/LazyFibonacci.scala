// src/script/scala/progscala3/typesystem/lazy/LazyFibonacci.scala

lazy val fib: LazyList[Int] =
  LazyList.cons(0,
    LazyList.cons(1, fib.zip(fib.tail).map(p => p._1 + p._2)))

assert(fib.take(10).force == LazyList(0, 1, 1, 2, 3, 5, 8, 13, 21, 34))

// src/main/scala/progscala2/typesystem/lazy/lazy-fibonacci.sc

lazy val fib: Stream[Int] = 
  Stream.cons(0, Stream.cons(1, fib.zip(fib.tail).map(p => p._1 + p._2)))
  
fib.take(10).print

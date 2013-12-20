// code-examples/TypeSystem/lazy/lazy-ints-script.scala

def from(n: Int): Stream[Int] = Stream.cons(n, from(n+1))

lazy val ints = from(0)
lazy val odds = ints.filter(_ % 2 == 1)
lazy val evens = ints.filter(_ % 2 == 0)

odds.take(10).print
evens.take(10).print

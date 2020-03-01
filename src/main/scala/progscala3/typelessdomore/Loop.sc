// src/main/scala/progscala3/typelessdomore/loop.sc

/** Do "pure side effect" work */
def loop(n: Int)(f: Int => Unit): Unit = {
  def lp(i: Int): Unit = {
    if (i <= n) { 
      f(i)
      lp(i + 1) 
    }
  }
  lp(1)
}

var accumulator: Seq[Int] = Nil
loop(4)(i => accumulator = i +: accumulator)
assert(accumulator == Seq(4, 3, 2, 1))

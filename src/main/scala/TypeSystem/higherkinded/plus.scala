// src/main/scala/TypeSystem/higherkinded/plus.scala

package TypeSystem.higherkinded

trait Plus[T] {                                                 // <1>
  def plus(t1: T, T2: T): T
}

object Plus {                                                   // <2>
  implicit val plusInt = new Plus[Int] {
    def plus(i1: Int, i2: Int): Int = i1 + i2
  }

  implicit val plusIntIntPair = new Plus[(Int,Int)] {
    def plus(p1: (Int,Int), p2: (Int,Int)): (Int,Int) = 
      (p1._1 + p2._1, p1._2 + p2._2)
  }
}

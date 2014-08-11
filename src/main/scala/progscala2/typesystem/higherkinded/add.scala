// src/main/scala/progscala2/typesystem/higherkinded/Add.scala
package progscala2.typesystem.higherkinded

trait Add[T] {                                                 // <1>
  def add(t1: T, T2: T): T
}

object Add {                                                   // <2>
  implicit val addInt = new Add[Int] {
    def add(i1: Int, i2: Int): Int = i1 + i2
  }

  implicit val addIntIntPair = new Add[(Int,Int)] {
    def add(p1: (Int,Int), p2: (Int,Int)): (Int,Int) = 
      (p1._1 + p2._1, p1._2 + p2._2)
  }
}

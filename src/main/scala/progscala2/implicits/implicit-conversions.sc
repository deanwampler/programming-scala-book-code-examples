// src/main/scala/progscala2/implicits/implicit-conversions.sc

object Implicits {
  implicit final class DarthVadarShip[A](val self: A) {
    def <-*-> [B](y: B): Tuple2[A, B] = Tuple2(self, y)
  }
}

import Implicits._

val m = Map("one" <-*-> 1, "two" <-*-> 2)

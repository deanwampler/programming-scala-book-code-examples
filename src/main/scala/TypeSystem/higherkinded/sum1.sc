// src/main/scala/TypeSystem/higherkinded/sum1.sc

import TypeSystem.higherkinded._
import Plus._

def sum[T : Plus](seq: Seq[T]): T = seq reduce (implicitly[Plus[T]].plus(_,_))

Sum.sum(Vector(1 -> 10, 2 -> 20, 3 -> 30))   // Result: (6,60)
Sum.sum(1 to 10)                             // Result: 55

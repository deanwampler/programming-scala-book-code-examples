// src/main/scala/progscala2/typesystem/higherkinded/add1.sc
import scala.language.higherKinds
import progscala2.typesystem.higherkinded.{Add, Reduce1}
import progscala2.typesystem.higherkinded.Add._
import progscala2.typesystem.higherkinded.Reduce1._

def sum1[T : Add, M[_] : Reduce1](container: M[T]): T =
    implicitly[Reduce1[M]].reduce(container)(implicitly[Add[T]].add(_,_))

sum1(Vector(1 -> 10, 2 -> 20, 3 -> 30))                    // Result: (6,60)
sum1(1 to 10)                                              // Result: 55
sum1(Option(2))                                            // Result: 2
sum1[Int,Option](None)                                     // <4> ERROR!

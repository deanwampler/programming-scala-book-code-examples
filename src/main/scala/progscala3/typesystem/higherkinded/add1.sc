// src/main/scala/progscala3/typesystem/higherkinded/add1.sc
// Add the following line if you don't use the compiler option
// "-language:higherKinds"
// import scala.language.higherKinds
import progscala3.typesystem.higherkinded.{Add, Reduce1}
import progscala3.typesystem.higherkinded.Add._
import progscala3.typesystem.higherkinded.Reduce1._

def sum[T : Add, M[_] : Reduce1](container: M[T]): T =
    implicitly[Reduce1[M]].reduce(container)(implicitly[Add[T]].add(_,_))

assert(sum(Vector(1 -> 10, 2 -> 20, 3 -> 30)) == (6 -> 60))
assert(sum(1 to 10)                           == 55)
assert(sum(Option(2))                         == 2)
// sum[Int,Option](None)                                   // <4> ERROR!

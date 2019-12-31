// src/main/scala/progscala3/typesystem/higherkinded/add.sc
// Add the following line if you don't use the compiler option
// "-language:higherKinds"
// import scala.language.higherKinds
import progscala3.typesystem.higherkinded.{Add, Reduce}    // <1>
import progscala3.typesystem.higherkinded.Add._
import progscala3.typesystem.higherkinded.Reduce._

def sum[T : Add, M[T]](container: M[T])(                   // <2>
  implicit red: Reduce[T,M]): T =
    red.reduce(container)(implicitly[Add[T]].add(_,_))

assert(sum(Vector(1 -> 10, 2 -> 20, 3 -> 30)) == (6,60))
assert(sum(1 to 10)                           == 55)
assert(sum(Option(2))                         == 2)
// sum[Int,Option](None)                                   // <3> ERROR!

// src/main/scala/ForComps/monads/for-steps.scala

package ForComps

import scala.language.higherKinds   // <1>

trait Process[T, M[T]] {            // <2>

  // Alias the function signature:
  type Step = T => M[T]             // <3>

  def flatMap[T2 >: T](source: M[T])(f: T => M[T2]): M[T2]  // <4>

  def run(countSteps: Seq[Step])(zero: M[T]): M[T] = {      // <5>
    (countSteps foldLeft zero) {
      (sumM, step) => flatMap(sumM)(i => step(i))           // <6>
    }
  }
}

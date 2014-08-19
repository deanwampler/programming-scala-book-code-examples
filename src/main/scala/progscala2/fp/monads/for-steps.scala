// src/main/scala/progscala2/fp/monads/for-steps.scala
package progscala2.fp.monads
import scala.language.higherKinds   // <1>

/**
 * A trait for defining "processes", parameterized by element and container
 * types.
 */
trait Process[T, M[T]] {

  /** Alias the "step" as a function. */
  type Step = T => M[T]

  /** Support flat-mapping  over a sequence. */
  def flatMap[T2 >: T](source: M[T])(f: T => M[T2]): M[T2]

  /** "Run" the steps. */
  def run(countSteps: Seq[Step])(zero: M[T]): M[T] = {
    (countSteps foldLeft zero) {
      (sumM, step) => flatMap(sumM)(i => step(i))
    }
  }
}

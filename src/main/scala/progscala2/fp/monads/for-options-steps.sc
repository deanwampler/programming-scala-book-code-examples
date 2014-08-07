// src/main/scala/progscala2/fp/monads/for-options-steps.sc

import progscala2.fp.monads.Process

val successfulOptionSteps: Seq[Int => Option[Int]] = List(  // <1>
  (i:Int) => Some(i + 5), 
  (i:Int) => Some(i + 10), 
  (i:Int) => Some(i + 25))
val partiallySuccessfulOptionSteps = List(
  (i:Int) => Some(i + 5), 
  (i:Int) => None,   // FAIL!
  (i:Int) => Some(i + 25))

val optionProcess = new Process[Int, Option] {
  def flatMap[T2 >: Int](                           // <2>
    source: Option[Int])(
    f: Int => Option[T2]): Option[T2] =
    source flatMap f
}

optionProcess.run(successfulOptionSteps)(Some(0))   // <3>
// Returns: Option[Int] = Some(40)

optionProcess.run(partiallySuccessfulOptionSteps)(Some(0))
// Returns: Option[Int] = None

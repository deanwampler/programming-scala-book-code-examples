// src/main/scala/progscala2/fp/categories/for-options-steps.sc

// Example of using option handling on an arbitrarily-long 
// sequence of functions that return Option[Int]. When the sequence
// is arbitrary, you can't use a for comprehension.
  
// Alias the function signature:
type Step = Int => Option[Int]

// A sequence of functions for the process steps. Each takes the result of
// the previous step (or a seed value to start) and returns a new option.
val successfulSteps = List(
  (i:Int) => Some(i + 5), 
  (i:Int) => Some(i + 10), 
  (i:Int) => Some(i + 25))
val partiallySuccessfulSteps = List(
  (i:Int) => Some(i + 5), 
  // A step that fails and indicates the failure by returning +None+.
  (i:Int) => None,   // FAIL!
  (i:Int) => Some(i + 25))

// Fold over the steps, starting with the seed value +0+, wrapped in a +Some+.
// Use +flatMap+ to extract the current sum value and pass it to the current 
// step, which returns a new +Option+ that becomes the next +sumOpt+. 
// If +sumOpt+ is actually a +None+, the anonymous function isn't called and
// +None+ is returned.
def sumCounts1(countSteps: Seq[Step]): Option[Int] = 
  (countSteps foldLeft Option(0)) {
    (sumOpt, step) => sumOpt flatMap (i => step(i))
  }

sumCounts1(successfulSteps)
// Returns: Option[Int] = Some(40)

sumCounts1(partiallySuccessfulSteps)
// Returns: Option[Int] = None

// More verbose, but it stops the "counts" iteration at the first None
// and it doesn't create intermediate Options:
// We sequence through the steps and sum the values returned. 
// A nested, tail-recursive function +sum+ is used.
def sumCounts2(countSteps: Seq[Step]): Option[Int] = {
  @annotation.tailrec
  def sum(accum: Int, countSteps2: Seq[Step]): Option[Int] = 
    countSteps2 match {
      // Terminate the recursion when we hit the end of the steps +Seq+.
      case Nil          => Some(accum)
      // Otherwise, the head of the list is a step, so we call it
      // with the current +accum+ value. We pattern match on the 
      // returned +Option+.
      case step +: tail => step(accum) match {
        // If +None+, terminate the recursion and return +None+.
        case None     => None
        // Otherwise, call +sum+ again with the new accumulated value 
        // and the steps tail.
        case Some(i2) => sum(i2, tail)
      }
    } 
  // Call the nested function with the seed sum +0+ and the sequence of steps.
  sum(0, countSteps)
}

sumCounts2(successfulSteps)
// Returns: Option[Int] = Some(40)

sumCounts2(partiallySuccessfulSteps)
// Returns: Option[Int] = None

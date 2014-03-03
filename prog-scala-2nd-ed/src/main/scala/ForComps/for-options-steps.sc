// src/main/scala/ForComps/for-options-steps.sc

// Alias the function signature:
type Step = Int => Option[Int]

val successfulSteps = List(  // <1>
  (i:Int) => Some(i + 5), 
  (i:Int) => Some(i + 10), 
  (i:Int) => Some(i + 25))
val partiallySuccessfulSteps = List(
  (i:Int) => Some(i + 5), 
  (i:Int) => None,   // FAIL!   <2>
  (i:Int) => Some(i + 25))

def sumCounts1(countSteps: Seq[Step]): Option[Int] = 
  (countSteps foldLeft Option(0)) {                   // <3>
    (sumOpt, step) => sumOpt flatMap (i => step(i))   // <4>
  }

sumCounts1(successfulSteps)
// Returns: Option[Int] = Some(40)

sumCounts1(partiallySuccessfulSteps)
// Returns: Option[Int] = None

// More verbose, but it stops the "counts" iteration at the first None
// and it doesn't create intermediate Options:
def sumCounts2(countSteps: Seq[Step]): Option[Int] = {  // <5>
  @annotation.tailrec
  def sum(accum: Int, countSteps2: Seq[Step]): Option[Int] = 
    countSteps2 match {
      case Nil          => Some(accum)            // <6>
      case step +: tail => step(accum) match {    // <7>
        case None     => None                     // <8>
        case Some(i2) => sum(i2, tail)            // <9>
      }
    } 
  sum(0, countSteps)                              // <10>
}

sumCounts2(successfulSteps)
// Returns: Option[Int] = Some(40)

sumCounts2(partiallySuccessfulSteps)
// Returns: Option[Int] = None

// src/main/scala/ForComps/for-options-steps.sc

val successfulCounts = List(Some(5), Some(10), Some(25))
val partiallySuccessfulCounts = List(Some(5), None, Some(25))

// A function to combine two Options by applying a function on their values.
def optionMap2[T1,T2,T3](                              // <1>
    opt1: Option[T1], opt2: Option[T2])(f: (T1,T2) => T3): Option[T3] = 
  (opt1, opt2) match {
    case (None, _)            => None                  // <2>
    case (_, None)            => None
    case (Some(t1), Some(t2)) => Option(f(t1, t2))     // <3>
  }

def sumCounts1(counts: Seq[Option[Int]]): Option[Int] = 
  (counts foldLeft Some(0)) {                          // <4>
    (sumOpt, opt) => optionMap2(sumOpt, opt)(_ + _)    // <5>
  }

sumCounts1(successfulCounts)
// Returns: Option[Int] = Some(40)

sumCounts1(partiallySuccessfulCounts)
// Returns: Option[Int] = None

// More verbose, but stops the "counts" iteration at the first None:
def sumCounts2(counts: Seq[Option[Int]]): Option[Int] = {   // <6>
  @annotation.tailrec
  def sum(accum: Option[Int], counts2: Seq[Option[Int]]): Option[Int] = 
    counts2 match {
      case None    +: tail => None                           // <7>
      case Some(i) +: tail => sum(accum map (_ + i), tail)   // <8>
      case Nil             => accum                          // <9>
    } 
  sum(Some(0), counts)                                       // <10>
}

sumCounts2(successfulCounts)
// Returns: Option[Int] = Some(40)

sumCounts2(partiallySuccessfulCounts)
// Returns: Option[Int] = None

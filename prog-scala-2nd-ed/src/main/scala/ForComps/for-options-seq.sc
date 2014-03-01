// src/main/scala/ForComps/for-options-seq.sc

val successfulCounts = List(Some(5), Some(10), Some(25))
val partiallySuccessfulCounts = List(Some(5), None, Some(25))

def sumCounts1(counts: List[Option[Int]]): Option[Int] = 
  (list foldLeft Some(0)) ((sumOpt, opt) => someOpt map )

// More verbose, but stops the iteration at the first None:
def sumCounts(counts: List[Option[Int]]): Option[Int] = {
  def sum(accum: Option[Int], counts2: List[Option[Int]]): Option[Int] = 
    counts2 match {
      case None    :: tail => None
      case Some(i) :: tail => sum(accum map (_ + i), tail)
      case Nil             => accum
    } 
  sum(Some(0), counts)
}

// Returns: Option[Int] = Some(40)
sumCounts(successfulCounts)

// Returns: Option[Int] = None
sumCounts(partiallySuccessfulCounts)

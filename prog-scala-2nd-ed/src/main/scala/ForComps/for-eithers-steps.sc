// src/main/scala/ForComps/for-eithers-steps.sc

val successfulCounts = List(Right(5), Right(10), Right(25))
val partiallySuccessfulCounts = List(
  Right(5), Left("EPIC"), Right(25), Left("FAIL!"))

// Combine two Eithers[A,B] by applying a function on their values.
// Note that two functions are required, for the left and right values:
def eitherMap2[A,B](                                  // <1>
    either1: Either[A,B], either2: Either[A,B])(
    fleft:  (A,A) => A)(                              // <2>
    fright: (B,B) => B): Either[A,B] =                // <3>
  (either1, either2) match {
    case (Left(a), Left(b))   => Left(fleft(a,b))     // <4>
    case (l @ Left(t), _       )   => l               // <5>
    case (_,        l @ Left(t))   => l               
    case (Right(a), Right(b)) => Right(fright(a,b))   // <6>
  }

def sumCounts1(counts: Seq[Either[String,Int]]): Either[String,Int] = {
  val seed: Either[String,Int] = Right(0)             // <7>
  (counts foldLeft seed) {                            // <8>
    (sumEither, either) => eitherMap2(sumEither, either)(
      (s1,s2) => s"$s1 $s2")(_ + _)                   // <9>
  }
}

sumCounts1(successfulCounts)
// Returns: Either[String,Int] = Right(40)

sumCounts1(partiallySuccessfulCounts)
// Returns: Either[String,Int] = Left(EPIC FAIL!)

// More verbose, but stops the "counts" iteration at the first Left:
def sumCounts2(counts: Seq[Either[String,Int]]): Either[String,Int] = {
  @annotation.tailrec
  def sum(accum: Int, counts2: Seq[Either[String,Int]]): Either[String,Int] = 
    counts2 match {
      case head +: tail => head match {
        case l @ Left(s)  => l
        case r @ Right(i) => sum(accum + i, tail)
      }
      case Nil => Right(accum)
    } 
  sum(0, counts)
}

sumCounts2(successfulCounts)
// Returns: Either[String,Int] = Right(40)

sumCounts2(partiallySuccessfulCounts)
// Returns: Either[String,Int] = Left(EPIC)

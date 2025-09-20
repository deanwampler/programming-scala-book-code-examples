// tag::definitions[]
// src/main/scala/progscala3/contexts/UsingClauses.scala
// This is identical to src/script/scala/progscala3/contexts/UsingClauses.scala,
// with the method invocations moved to a new @main function at the end.
// I created it just to make it easier to test some work, and decided it 
// "doesn't hurt" to keep it around, but only the script version is used in the
// book.

case class SortableSeq[A](seq: Seq[A]):
  def sortBy1a[B](transform: A => B)(using o: Ordering[B]): SortableSeq[A] =
    SortableSeq(seq.sortBy(transform)(using o))

  def sortBy1b[B](transform: A => B)(using Ordering[B]): SortableSeq[A] =
    SortableSeq(seq.sortBy(transform)(using summon[Ordering[B]]))

  def sortBy2[B : Ordering](transform: A => B): SortableSeq[A] =
    SortableSeq(seq.sortBy(transform)(using summon[Ordering[B]]))
// end::definitions[]

// tag::defaultOrdering[]
def defaultOrdering() =
  val seq = SortableSeq(Seq(1,3,5,2,4))
  val expected = SortableSeq(Seq(5, 4, 3, 2, 1))
  assert(seq.sortBy1a(i => -i) == expected)
  assert(seq.sortBy1b(i => -i) == expected)
  assert(seq.sortBy2(i => -i)  == expected)

// end::defaultOrdering[]

// tag::oddEvenImplicitOrdering[]
def oddEvenImplicitOrdering() =
  implicit val oddEven: Ordering[Int] = new Ordering[Int]:
    def compare(i: Int, j: Int): Int = i%2 compare j%2 match
      case 0 => i compare j
      case c => c

  val seq = SortableSeq(Seq(1,3,5,2,4))
  val expected = SortableSeq(Seq(5, 3, 1, 4, 2))
  assert(seq.sortBy1a(i => -i) == expected)
  assert(seq.sortBy1b(i => -i) == expected)
  assert(seq.sortBy2(i => -i)  == expected)

  assert(seq.sortBy1a(i => -i)(using oddEven) == expected)
  assert(seq.sortBy1b(i => -i)(using oddEven) == expected)
  assert(seq.sortBy2(i => -i)(using oddEven)  == expected)

// end::oddEvenImplicitOrdering[]

// tag::oddEvenGivenOrdering[]
def evenOddGivenOrdering() =
  given evenOdd: Ordering[Int]:
    def compare(i: Int, j: Int): Int = i%2 compare j%2 match
      case 0 => i compare j
      case c => -c

  val seq = SortableSeq(Seq(1,3,5,2,4))
  val expected = SortableSeq(Seq(4, 2, 5, 3, 1))
  assert(seq.sortBy1a(i => -i) == expected)                     // <1>
  assert(seq.sortBy1b(i => -i) == expected)
  assert(seq.sortBy2(i => -i)  == expected)

  assert(seq.sortBy1a(i => -i)(using evenOdd) == expected)      // <2>
  assert(seq.sortBy1b(i => -i)(using evenOdd) == expected)
  assert(seq.sortBy2(i => -i)(using evenOdd)  == expected)

// end::oddEvenGivenOrdering[]

@main def checkUsingClauses() =
  defaultOrdering()
  oddEvenImplicitOrdering()
  evenOddGivenOrdering()

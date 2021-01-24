// tag::definitions[]
// src/script/scala/progscala3/contexts/UsingClauses.scala

case class SortableSeq[A](seq: Seq[A]):
  def sortBy1a[B](transform: A => B)(using o: Ordering[B]): SortableSeq[A] =
    SortableSeq(seq.sortBy(transform)(o))

  def sortBy1b[B](transform: A => B)(using Ordering[B]): SortableSeq[A] =
    SortableSeq(seq.sortBy(transform)(summon[Ordering[B]]))

  def sortBy2[B : Ordering](transform: A => B): SortableSeq[A] =
    SortableSeq(seq.sortBy(transform)(summon[Ordering[B]]))
// end::definitions[]

// tag::defaultOrdering[]
def defaultOrdering() =
  val seq = SortableSeq(Seq(1,3,5,2,4))
  val expected = SortableSeq(Seq(5, 4, 3, 2, 1))
  assert(seq.sortBy1a(i => -i) == expected)
  assert(seq.sortBy1b(i => -i) == expected)
  assert(seq.sortBy2(i => -i)  == expected)

defaultOrdering()
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

oddEvenImplicitOrdering()
// end::oddEvenImplicitOrdering[]

// tag::oddEvenGivenOrdering[]
def evenOddGivenOrdering() =
  given evenOdd: Ordering[Int] with
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

evenOddGivenOrdering()
// end::oddEvenGivenOrdering[]

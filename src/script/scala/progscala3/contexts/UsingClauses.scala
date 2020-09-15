// src/script/scala/progscala3/implicits/UsingClauses.scala

case class SortableSeq[A](seq: Seq[A]):                              // <1>
  def sortBy1a[B](transform: A => B)(using o: Ordering[B]): SortableSeq[A] =
    new SortableSeq(seq.sortBy(transform)(o))

  def sortBy1b[B](transform: A => B)(using Ordering[B]): SortableSeq[A] =
    new SortableSeq(seq.sortBy(transform)(summon[Ordering[B]]))

  def sortBy2[B : Ordering](transform: A => B): SortableSeq[A] =
    new SortableSeq(seq.sortBy(transform)(summon[Ordering[B]]))

val seq = SortableSeq(Seq(1,3,5,2,4))

def defaultOrdering() =
  val expected = SortableSeq(Seq(5, 4, 3, 2, 1))
  assert(seq.sortBy1a(i => -i) == expected)
  assert(seq.sortBy1b(i => -i) == expected)
  assert(seq.sortBy2(i => -i)  == expected)

defaultOrdering()

def oddEvenOrdering() =
  implicit val oddEven: Ordering[Int] = new Ordering[Int]:
    def compare(i: Int, j: Int): Int = i%2 compare j%2 match
      case 0 => i compare j
      case c => c

  val expected = SortableSeq(Seq(5, 3, 1, 4, 2))
  assert(seq.sortBy1a(i => -i) == expected)
  assert(seq.sortBy1b(i => -i) == expected)
  assert(seq.sortBy2(i => -i)  == expected)

  assert(seq.sortBy1a(i => -i)(using oddEven) == expected)
  assert(seq.sortBy1b(i => -i)(using oddEven) == expected)
  assert(seq.sortBy2(i => -i)(using oddEven)  == expected)

oddEvenOrdering()

def evenOddGivenOrdering() =
  given evenOdd as Ordering[Int] = new Ordering[Int]:
    def compare(i: Int, j: Int): Int = i%2 compare j%2 match
      case 0 => i compare j
      case c => -c

  val expected = SortableSeq(Seq(4, 2, 5, 3, 1))
  assert(seq.sortBy1a(i => -i) == expected)
  assert(seq.sortBy1b(i => -i) == expected)
  assert(seq.sortBy2(i => -i)  == expected)

  assert(seq.sortBy1a(i => -i)(using evenOdd) == expected)
  assert(seq.sortBy1b(i => -i)(using evenOdd) == expected)
  assert(seq.sortBy2(i => -i)(using evenOdd)  == expected)

evenOddGivenOrdering()


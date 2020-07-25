// src/script/scala/progscala3/implicits/ImplicitParameters3.scala

case class SortableSeq[A](seq: Seq[A]) {
  def sortBy1[B](transform: A => B)(using o: Ordering[B]): SortableSeq[A] =
    new SortableSeq(seq.sortBy(transform)(o))

  def sortBy2[B](transform: A => B)(using Ordering[B]): SortableSeq[A] =
    new SortableSeq(seq.sortBy(transform)(implicitly[Ordering[B]]))
}

val seq = SortableSeq(Seq(1,3,5,2,4))

def defaultOrdering() = {
	assert(seq.sortBy1(i => -i) == SortableSeq(Seq(5, 4, 3, 2, 1)))
	assert(seq.sortBy2(i => -i) == SortableSeq(Seq(5, 4, 3, 2, 1)))
}
defaultOrdering()

def oddEvenOrdering() = {
	implicit val oddEven: Ordering[Int] = new Ordering[Int] {
		def compare(i: Int, j: Int): Int = i%2 compare j%2 match {
			case 0 => i compare j
			case c => c
		}
	}
	assert(seq.sortBy1(i => -i) == SortableSeq(Seq(5, 3, 1, 4, 2)))
	assert(seq.sortBy2(i => -i) == SortableSeq(Seq(5, 3, 1, 4, 2)))
}
oddEvenOrdering()

def evenOddGivenOrdering() = {
	given evenOdd as Ordering[Int] = new Ordering[Int] {
		def compare(i: Int, j: Int): Int = i%2 compare j%2 match {
			case 0 => i compare j
			case c => -c
		}
	}
	assert(seq.sortBy1(i => -i) == SortableSeq(Seq(4, 2, 5, 3, 1)))
	assert(seq.sortBy2(i => -i) == SortableSeq(Seq(4, 2, 5, 3, 1)))
}
evenOddGivenOrdering()


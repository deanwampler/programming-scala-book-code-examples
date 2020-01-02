// src/main/scala/progscala3/fp/combinators/combinators.sc

object Combinators1 {
  def map[A,B](list: List[A])(f: (A) => B): List[B] = list map f
}

object Combinators {
  def map[A,B](f: (A) => B)(list: List[A]): List[B] = list map f
}

val intToString = (i:Int) => s"N=$i"
// Result: intToString: Int => String = <function1>

val flist = Combinators.map(intToString) _
// Result: flist: List[Int] => List[String] = <function1>

val list = flist(List(1, 2, 3, 4))
assert(list == List[String]("N=1", "N=2", "N=3", "N=4"))

val list1 = Combinators1.map(List(1, 2, 3, 4))(intToString)
assert(list1 == List[String]("N=1", "N=2", "N=3", "N=4"))

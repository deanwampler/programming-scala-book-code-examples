// src/main/scala/progscala3/metaprogramming/mkSeq.sc
import scala.reflect.ClassTag

def mkSeq[T : ClassTag](elems: T*) = Seq[T](elems: _*)

assert(mkSeq(1, 2, 3) == List[Int](1, 2, 3))
assert(mkSeq("one", "two", "three") == List[String]("one", "two", "three"))
assert(mkSeq(1, "two", 3.14) == List[Any](1, "two", 3.14))

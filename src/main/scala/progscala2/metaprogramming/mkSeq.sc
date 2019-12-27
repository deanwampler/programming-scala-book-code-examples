// src/main/scala/progscala2/metaprogramming/mkArray.sc
import scala.reflect.ClassTag

def mkArray[T : ClassTag](elems: T*) = Array[T](elems: _*)

mkArray(1, 2, 3)
mkArray("one", "two", "three")
mkArray(1, "two", 3.14)

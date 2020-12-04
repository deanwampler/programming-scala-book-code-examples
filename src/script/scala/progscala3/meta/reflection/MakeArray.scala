// src/script/scala/progscala3/meta/reflection/MakeArray.scala

import scala.reflect.ClassTag

def makeArray[T : ClassTag](elems: T*) = Array[T](elems: _*)    // <1>

makeArray(1, 2, 3)
makeArray(1.1, 2.2, 3.3)
makeArray("one", "two", "three")
makeArray("one" -> 1, "two" -> 2, "three" -> 3)
makeArray(1, 2.2, 3L)


def makeArray2[T](elems: T*) = Array[T](elems: _*)

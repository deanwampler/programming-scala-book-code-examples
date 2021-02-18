// src/script/scala/progscala3/meta/reflection/MakeArray.scala

import scala.reflect.ClassTag

def makeArray[T : ClassTag](elems: T*) = Array[T](elems*)  // <1>

makeArray(1, 2, 3)
makeArray(1.1, 2.2, 3.3)
makeArray("one", "two", "three")
makeArray("one" -> 1, "two" -> 2, "three" -> 3)
makeArray(1, 2.2, 3L)

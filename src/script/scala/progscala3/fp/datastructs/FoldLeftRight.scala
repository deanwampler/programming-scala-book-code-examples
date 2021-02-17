// src/script/scala/progscala3/fp/datastructs/FoldLeftRight.scala

val seq6 = Seq(1,2,3,4,5,6)
val int1 = seq6.fold(0)(_ + _)
val int2 = seq6.foldLeft(0)(_ + _)
val int3 = seq6.foldRight(0)(_ + _)

val int4 = seq6.foldLeft(0)((accum: Int, element: Int) => accum + element)
val int5 = seq6.foldRight(0)((element: Int, accum: Int) => element + accum)

val left = (accum: String, element: Int) => s"($accum $element)"
val right = (element: Int, accum: String) => s"($accum $element)"
val right2 = (element: Int, accum: String) => s"($element $accum)"

val strLeft = seq6.foldLeft("(0)")(left)
val strRight = seq6.foldRight("(0)")(right)
val strRight2 = seq6.foldRight("(0)")(right2)

import progscala3.fp.datastructs.FoldLeftRight.*

val strLeft3 = foldLeft(seq6)("(0)")(left)
val strRight3 = foldRight(seq6)("(0)")(right)
val strRight4 = foldRight(seq6)("(0)")(right2)

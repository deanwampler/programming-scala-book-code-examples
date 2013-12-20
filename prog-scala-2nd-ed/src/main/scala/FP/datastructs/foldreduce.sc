// code-examples/FP/datastructs/foldreduce-script.scala

List(1,2,3,4,5,6) reduceLeft(_ + _)

List(1,2,3,4,5,6).foldLeft(10)(_ * _)

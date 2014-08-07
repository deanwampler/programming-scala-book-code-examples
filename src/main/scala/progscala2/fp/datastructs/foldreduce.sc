// src/main/scala/progscala2/fp/datastructs/foldreduce.sc

List(1,2,3,4,5,6) reduce (_ + _)

List(1,2,3,4,5,6).fold (10) (_ * _)
(List(1,2,3,4,5,6) fold 10) (_ * _)

val fold1 = (List(1,2,3,4,5,6) fold 10) _
fold1(_ * _)

(List.empty[Int] fold 10) (_ + _)
try {
  List.empty[Int] reduce (_ + _)
} catch {
  case e: java.lang.UnsupportedOperationException => e
}

List.empty[Int] optionReduce (_ + _)
List(1,2,3,4,5,6) optionReduce (_ * _)

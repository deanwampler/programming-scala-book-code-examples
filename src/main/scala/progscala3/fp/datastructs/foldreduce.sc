// src/main/scala/progscala3/fp/datastructs/foldreduce.sc

val r1 = List(1,2,3,4,5,6) reduce (_ + _)
val r2 = List(1,2,3,4,5,6).reduce (_ + _)
assert(r1 == 21 && r2 == 21)

val f1 = List(1,2,3,4,5,6).fold (10) (_ * _)
val f2 = (List(1,2,3,4,5,6) fold 10) (_ * _)
assert(f1 == 7200 && f2 == 7200)

val fold1 = (List(1,2,3,4,5,6) fold 10) _
println(fold1(_ * _))

val f3 = (List.empty[Int] fold 10) (_ + _)
assert(f3 == 10)

try {
  List.empty[Int] reduce (_ + _)
  assert(false, "Should not be here!")
} catch {
  case e: java.lang.UnsupportedOperationException => e  // expected
}

val r3 = List.empty[Int] reduceOption (_ + _)               // <1>
assert(r3 == None)

val r4 = List(1,2,3,4,5,6) reduceOption (_ * _)
assert(r4 == Some(720))

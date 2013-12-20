// code-examples/TypeSystem/lazy/lazy-range-danger-script.scala

def mkRandomInts() = {
  val randInts = for {
    i <- 1 to 3
    val rand = i + (new scala.util.Random).nextInt
  } yield rand
  randInts
}
val ints1 = mkRandomInts

println("Calling first on ints1 Seq:")
for (i <- 1 to 3) {
  println( ints1.first)
}

val ints2 = ints1.toList
println("Calling first on List created from ints1 Seq:")
for (i <- 1 to 3) {
  println( ints2.first)
}

// src/main/scala/progscala2/fp/datastructs/fibonacci.sc
import scala.math.BigInt

val fibs: Stream[BigInt] =
  BigInt(0) #:: BigInt(1) #:: fibs.zip(fibs.tail).map (n => n._1 + n._2)

fibs take 10 foreach (i => print(s"$i "))

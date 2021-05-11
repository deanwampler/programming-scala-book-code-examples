// src/script/scala/progscala3/forcomps/ForEithersCombinators.scala

val result1: Either[String, Int] = Right(3805)
val result2: Either[String, Int] = Left("nonpositive number -5")

result1
result2

var r1  = 0
result1.foreach(i => r1 = i * 2)
var r2  = 0
result2.foreach(i => r2 = i * 2)

val r3  = result1.map(_ * 2)
val r4  = result2.map(_ * 2)

val r5a = result1.flatMap(i => Right(i * 2))
val r5b = result1.flatMap(i => Left("hello"))
val r5c = result1.flatMap(i => Left[String,Double]("hello"))
val r5d: Either[String,Double] = result1.flatMap(i => Left("hello"))
val r6  = result2.flatMap(i => Right(i * 2))

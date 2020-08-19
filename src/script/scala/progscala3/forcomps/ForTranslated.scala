// src/script/scala/progscala3/forcomps/ForTranslated.scala

val seq = Seq(1,2,3)

for i <- seq yield 2*i
seq.map { case i => 2*i }

var sum1 = 0
for i <- seq do sum1 += 1
var sum2 = 0
seq.foreach { case i => sum2 += 1 }

for
  i <- seq
  if i%2 != 0
yield 2*i

for
  i <- seq if i%2 != 0
yield 2*i

seq.withFilter {
  case i if i%2 != 0 => true
  case _ => false
}.map { case i => 2*i }

for
  i <- seq
  j <- (i to 3)
yield j

seq.flatMap { case i => for j <- (i to 3) yield j }      // <1>
seq.flatMap { case i => (i to 3).map { case j => j } }   // <2>

var sum3=0
for
  i <- seq
  j <- (i to 3)
do sum3 += j

var sum4=0
seq.foreach { case i => for j <- (i to 3) do sum4 += j }
var sum5=0
seq.foreach { case i => (i to 3).foreach { case j => sum5 += j } }

for
  i <- seq
  i10 = i*10
yield i10

for
  (i, i10) <- for
    x1 @ i <- seq
  yield
    val x2 @ i10 = x1*10
    (x1, x2)
yield i10

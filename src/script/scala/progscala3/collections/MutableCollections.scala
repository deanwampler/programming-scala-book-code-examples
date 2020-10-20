// src/script/scala/progscala3/collections/MutableCollections.scala
import collection.mutable.ArrayBuffer                       // <1>

val seq = ArrayBuffer(0)                                      // <2>

seq ++=  Seq(1, 2)
seq.appendAll(Seq(3, 4))
seq += 5
seq.append(6)

Seq(-2, -1) ++=: seq
seq.prependAll(Seq(-4, -3))
-5 +=: seq
seq.prepend(-6)

seq -= -6
seq.subtractOne(6)
seq --= Seq(-2, -4)
seq.subtractAll(Seq(2, 4))

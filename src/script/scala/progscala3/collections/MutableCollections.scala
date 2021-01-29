// src/script/scala/progscala3/collections/MutableCollections.scala
import collection.mutable.ArrayBuffer

val seq = ArrayBuffer(0)

seq ++=  Seq(1, 2)               // Alias for appendAll
seq.appendAll(Seq(3, 4))         // Append a sequence
seq += 5                         // Alias for addOne
seq.addOne(6)                    // Append one element
seq.append(7)                    // Append one element
assert(seq == ArrayBuffer(0, 1, 2, 3, 4, 5, 6, 7))

Seq(-2, -1) ++=: seq             // Alias for prependAll
seq.prependAll(Seq(-4, -3))      // Prepend a sequence
-5 +=: seq                       // Alias for prepend
seq.prepend(-6)                  // Prepend one element
assert(seq == ArrayBuffer(-6, -5, -4, -3, -2, -1, 0, 1, 2, 3, 4, 5, 6, 7))

seq -= -6                        // Alias for subtractOne
seq.subtractOne(7)               // Remove the element
seq --= Seq(-2, -4)              // Alias for subtractAll
seq.subtractAll(Seq(2, 4))       // Remove a sequence
assert(seq == ArrayBuffer(-5, -3, -1, 0, 1, 3, 5, 6))

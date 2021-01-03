// src/script/scala/progscala3/basicoop/Tuple.scala

val t1 = (1, "two", 3.3)
val t2 = (4.4F, (5L, 6.6))

val t01 = 0L *: t1                // Prepend an element
val t12 = t1 ++ t2                // Concatenate tuples

val one *: two *: three *: four *: EmptyTuple = t01

val t12d3 = t12.drop(3)           // Drop leading 3 elements
val t12d4 = t12.drop(4)           // Drop leading 4 elements
val t12t3 = t12.take(3)
val t12s3 = t12.splitAt(3)        // Like take and drop combined
val t12s4 = t12.splitAt(4)        // Like take and drop combined

val a  = t1.toArray               // Convert to collections
val ia = t1.toIArray
val l  = t1.toList

val ta  = Tuple.fromArray(a)      // Convert from collections
val tia = Tuple.fromIArray(ia)
// val tl  = Tuple.fromList(l)    // Doesn't exist
case class Person(name: String, age: Int)
val tp = Tuple.fromProduct(Person("Dean", 29))

val z1 = t1.zip(t2)               // Note that t1's "3.3" is dropped
val z2 = t1.zip((4.4,5,6.6))      // Two, three-element tuples zipped

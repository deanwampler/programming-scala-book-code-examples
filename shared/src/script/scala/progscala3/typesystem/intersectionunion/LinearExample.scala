// src/script/scala/progscala3/typesystem/intersectionunion/LinearExample.scala
import progscala3.typesystem.intersectionunion._

val c12 = new C with T1 with T2
val c21 = new C with T2 with T1

assert(c12.m("hello") == "( [ { hello } ] )")
assert(c21.m("hello") == "[ ( { hello } ) ]")

val t1a: T1 = c12
val t2a: T2 = c12
val c2a: C  = c12

val t1b: T1 = c21
val t2b: T2 = c21
val c2b: C  = c21

val c12a: C & T1 & T2 = c12
val c12b: C & T1 & T2 = c21

val c21a: C & T2 & T1 = c12
val c21b: C & T2 & T1 = c21
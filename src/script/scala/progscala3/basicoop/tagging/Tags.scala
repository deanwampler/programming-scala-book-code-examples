// src/script/scala/progscala3/basicoop/tagging/Tags.scala

import progscala3.basicoop.tagging.Tagging.*

trait Meter
trait Foot

val x: Double @@ Meter = (123.0).tag
val y: Double @@ Foot = (123.0).tag
val xs: Array[Double @@ Meter] = Array(1.0, 2.0, 3.0).tags
(x.untag, y.untag, xs.untags)

val o: Ordering[Double] = implicitly
val om: Ordering[Double @@ Meter] = o.tags
om.compare(x, x)
om.compare(x, y)  // Compilation Error!
xs.min(om)
xs.min(o)         // Compilation Error!

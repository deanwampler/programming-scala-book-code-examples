// src/script/scala/progscala3/basicoop/tagging/Tags2.scala

import progscala3.basicoop.tagging.Tagging2.*

trait Meter
trait Foot

val x: Double @@ Meter = (123.0).tag[Meter]
val y: Double @@ Foot = (123.0).tag[Foot]
val xs = Array(1.0, 2.0, 3.0).tags[Meter]
(x.untag, y.untag, xs.untags)

val o: Ordering[Double] = implicitly
val om: Ordering[Double @@ Meter] = o.tags
om.compare(x, x)
om.compare(x, y)  // Compilation Error!
xs.min(om)
xs.min(o)         // Compilation Error!

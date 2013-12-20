// code-examples/Traits/ui/button-count-observer-script.scala

import traits._

val bco = new ui.ButtonCountObserver
val oldCount = bco.count
bco.count = 5
val newCount = bco.count
println(newCount + " == 5 and " + oldCount + " == 0?")

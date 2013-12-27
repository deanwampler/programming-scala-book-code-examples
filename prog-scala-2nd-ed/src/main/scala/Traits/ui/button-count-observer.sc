// src/main/scala/Traits/ui/button-count-observer.sc

import traits._

val bco = new ui.ButtonCountObserver
val oldCount = bco.count
bco.count = 5
val newCount = bco.count
println(newCount + " == 5 and " + oldCount + " == 0?")

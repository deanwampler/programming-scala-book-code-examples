// src/main/scala/Traits/ui2/click-count-observer.sc

import traits.ui2._
import traits.observer._

// No override of "click" in Button required.
val button = new Button("Click Me!") with ObservableClicks

class ClickCountObserver extends Observer[Clickable] {
  var count = 0
  def receiveUpdate(state: Clickable): Unit = count += 1
}

val bco1 = new ClickCountObserver
val bco2 = new ClickCountObserver

button addObserver bco1
button addObserver bco2

(1 to 5) foreach (_ => button.click())

assert(bco1.count == 5, s"bco1.count ${bco1.count} != 5")
assert(bco2.count == 5, s"bco2.count ${bco2.count} != 5")
println("Success!")

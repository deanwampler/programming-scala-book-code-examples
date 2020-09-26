// src/script/scala/progscala3/traits/ui2/ClickCountObserver.scala
import progscala3.traits.ui2._
import progscala3.traits.observer._

// No override of "click" in Button required.
val button = new Button("Button") with ObservableClicks:
  def updateUI(): String = s"$label clicked"

val cco = new ClickCountObserver
button addObserver cco

(1 to 5) foreach (_ => assert("Button clicked" == button.click()))
assert(cco.clickCount == 5)

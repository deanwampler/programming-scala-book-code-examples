// src/script/scala/progscala3/traits/ui2/ClickCountObserver.scala
import progscala3.traits.ui2.*
import progscala3.traits.observer.*

// No override of "click" in Button required.
val button = new Button("Button") with ObservableClicks:
  def updateUI(): String = s"$label clicked"

val cco = new CountObserver[Clickable] {}                            // <1>
button.addObserver(cco)

(1 to 5) foreach (_ => assert("Button clicked" == button.click()))
assert(cco.count == 5)

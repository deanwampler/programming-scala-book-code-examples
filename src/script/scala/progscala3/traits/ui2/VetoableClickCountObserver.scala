// src/script/scala/progscala3/traits/ui2/VetoableClickCountObserver.scala
import progscala3.traits.ui2._
import progscala3.traits.observer._

val button = new Button("Button!")
    with ObservableClicks with VetoableClicks(maxAllowed = 2):
  def updateUI(): String = s"$label clicked"

val cco = new ClickCountObserver
button addObserver cco

(1 to 5) map (_ => button.click())
assert(cco.count == 2)

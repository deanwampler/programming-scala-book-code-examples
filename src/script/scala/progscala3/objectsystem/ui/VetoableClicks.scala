// src/script/scala/progscala3/objectsystem/ui/VetoableClicks.scala
import progscala3.objectsystem.ui.Button
import progscala3.traits.ui2.{Clickable, ObservableClicks, VetoableClicks}
import progscala3.traits.observer.*

val observableButton = new Button("Okay")                            // <1>
  with ObservableClicks with VetoableClicks(maxAllowed = 2)          // <2>

assert(observableButton.maxAllowed == 2,                             // <3>
  s"maxAllowed = ${observableButton.maxAllowed}")

class ClickCountObserver extends Observer[Clickable]:                // <4>
  var count = 0
  def receiveUpdate(state: Clickable): Unit = count += 1

val clickCountObserver = ClickCountObserver()                        // <5>
observableButton.addObserver(clickCountObserver)

val n = 5
for i <- 1 to n do observableButton.click()                          // <6>

assert(clickCountObserver.count == 2,                                // <7>
  s"count = ${clickCountObserver.count}. Should be != $n")

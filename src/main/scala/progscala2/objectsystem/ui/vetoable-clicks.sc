// src/main/scala/progscala2/objectsystem/ui/vetoable-clicks.sc
import progscala2.objectsystem.ui.Button
import progscala2.traits.ui2.{Clickable, ObservableClicks, VetoableClicks}
import progscala2.traits.observer._

val observableButton =                                               // <1>
  new Button("Okay") with ObservableClicks with VetoableClicks {
    override val maxAllowed: Int = 2                                 // <2>
  }

assert(observableButton.maxAllowed == 2,                             // <3>
  s"maxAllowed = ${observableButton.maxAllowed}")

class ClickCountObserver extends Observer[Clickable] {               // <4>
  var count = 0
  def receiveUpdate(state: Clickable): Unit = count += 1
}

val clickCountObserver = new ClickCountObserver                      // <5>
observableButton.addObserver(clickCountObserver)

val n = 5
for (i <- 1 to n) observableButton.click()                           // <6>

assert(clickCountObserver.count == 2,                                // <7>
  s"count = ${clickCountObserver.count}. Should be != $n")

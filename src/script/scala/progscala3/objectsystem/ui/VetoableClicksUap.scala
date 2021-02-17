// src/script/scala/progscala3/objectsystem/ui/VetoableClicksUap.scala
import progscala3.objectsystem.ui.Button
import progscala3.traits.ui2.{Clickable, ObservableClicks}
import progscala3.traits.observer.*

trait VetoableClicksUAP(maxAllowed: Int = 1) extends Clickable: // <1>

  private var count = 0

  abstract override def click(): String =
    if count < maxAllowed then
      count += 1
      super.click()
    else
      s"exceeded maximum allowed clicks $maxAllowed"

val observableButton = new Button("Okay")
  with ObservableClicks with VetoableClicksUAP(maxAllowed = 2)  // <2>

class ClickCountObserver extends Observer[Clickable]:
  var count = 0
  def receiveUpdate(state: Clickable): Unit = count += 1

val clickCountObserver = ClickCountObserver()
observableButton.addObserver(clickCountObserver)

val n = 5
for i <- 1 to n do observableButton.click()

assert(clickCountObserver.count == 2,
  s"count = ${clickCountObserver.count}. Should be != $n")

// src/main/scala/AdvOOP/ui/vetoable-clicks.scala

package advoop.ui
import traits.observer._

trait VetoableClicks extends Clickable {
  var maxAllowed = 1       // default
  private var count = 0
  abstract override def click() = {
    count += 1
    if (count <= maxAllowed)
      super.click()
  }
}

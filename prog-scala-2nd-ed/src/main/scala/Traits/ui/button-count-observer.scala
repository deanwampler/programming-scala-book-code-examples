// code-examples/Traits/ui/button-count-observer.scala

package traits.ui
import traits.observer._

class ButtonCountObserver {
  var count = 0
  def receiveUpdate(subject: Any) = count += 1
}

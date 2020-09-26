// src/main/scala/progscala3/traits/ui2/ClickCountObserver.scala
package progscala3.traits.ui2
import progscala3.traits.observer._

class ClickCountObserver extends Observer[Clickable]:
  var count = 0
  def receiveUpdate(state: Clickable): Unit = count += 1

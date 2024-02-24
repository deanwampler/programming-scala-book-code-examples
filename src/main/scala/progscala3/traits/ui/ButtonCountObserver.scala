// src/main/scala/progscala3/traits/ui/ButtonCountObserver.scala
package progscala3.traits.ui
import progscala3.traits.observer.*

/*
 * 2024-02-24: Changed the original count.synchronized {...} to this.synchronized {...},
 * because the former was wrong for a primitive. So now we synchronize on the instance.
 */
class ButtonCountObserver extends Observer[Button]:
  var count = 0
  def receiveUpdate(state: Button): Unit =
    this.synchronized { count += 1 } 

// src/main/scala/progscala3/traits/ui2/CountObserver.scala
package progscala3.traits.ui2
import progscala3.traits.observer.*

/*
 * 2024-02-24: Changed the original count.synchronized {...} to this.synchronized {...},
 * because the former was wrong for a primitive. So now we synchronize on the instance.
 */
trait CountObserver[State] extends Observer[State]:
  var count = 0
  def receiveUpdate(state: State): Unit = this.synchronized { count += 1 }

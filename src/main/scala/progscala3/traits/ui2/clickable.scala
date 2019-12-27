// src/main/scala/progscala3/traits/ui2/Clickable.scala
package progscala3.traits.ui2                                        // <1>

trait Clickable {
  def click(): Unit = updateUI()                                     // <2>

  protected def updateUI(): Unit                                     // <3>
}

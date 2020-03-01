// src/main/scala/progscala3/traits/ui2/Clickable.scala
package progscala3.traits.ui2                                        // <1>

trait Clickable {
  def click(): String = updateUI()                                   // <2>

  protected def updateUI(): String                                   // <3>
}

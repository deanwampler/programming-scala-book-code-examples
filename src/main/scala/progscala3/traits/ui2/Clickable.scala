// src/main/scala/progscala3/traits/ui2/Clickable.scala
package progscala3.traits.ui2                                        // <1>

trait Clickable:                                                     // <2>
  def click(): String = updateUI()
  protected def updateUI(): String

// src/main/scala/Traits/ui2/clickable.scala

package traits.ui2                     // <1>

trait Clickable {
  def click(): Unit = updateUI()       // <2>

  protected def updateUI(): Unit       // <3>
}

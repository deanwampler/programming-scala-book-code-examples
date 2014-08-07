// src/main/scala/progscala2/traits/ui2/clickable.scala

package progscala2.traits.ui2          // <1>

trait Clickable {
  def click(): Unit = updateUI()       // <2>

  protected def updateUI(): Unit       // <3>
}

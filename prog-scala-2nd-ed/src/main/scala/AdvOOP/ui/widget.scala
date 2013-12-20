// code-examples/AdvOOP/ui3/widget.scala

package advoop.ui

abstract class Widget {
  def draw(): Unit
  override def toString() = "(widget)"
}


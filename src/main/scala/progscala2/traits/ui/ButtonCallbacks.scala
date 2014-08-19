// src/main/scala/progscala2/traits/ui/ButtonCallbacks.scala
package progscala2.traits.ui

class ButtonWithCallbacks(val label: String,
    val callbacks: List[() => Unit] = Nil) extends Widget {

  def click(): Unit = {
    updateUI()
    callbacks.foreach(f => f())
  }

  protected def updateUI(): Unit = { /* logic to change GUI appearance */ }
}

object ButtonWithCallbacks {

  def apply(label: String, callback: () => Unit) =
    new ButtonWithCallbacks(label, List(callback))

  def apply(label: String) =
    new ButtonWithCallbacks(label, Nil)
}


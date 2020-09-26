// src/main/scala/progscala3/traits/ui/ButtonCallbacks.scala
package progscala3.traits.ui

abstract class ButtonWithCallbacks(val label: String,
    val callbacks: Seq[() => Unit] = Nil) extends Widget:            // <1>

  def click(): Unit =                                                // <2>
    updateUI()
    callbacks.foreach(f => f())

  protected def updateUI(): Unit                                     // <3>

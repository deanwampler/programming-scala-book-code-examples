// code-examples/Traits/ui/button-callbacks.scala

package traits.ui

class ButtonWithCallbacks(val label: String, 
    val clickedCallbacks: List[() => Unit]) extends Widget {

  require(clickedCallbacks != null, "Callback list can't be null!")

  def this(label: String, clickedCallback: () => Unit) =
    this(label, List(clickedCallback))

  def this(label: String) = {
    this(label, Nil)
    println("Warning: button has no click callbacks!")
  }

  def click() = {
    // ... logic to give the appearance of clicking a physical button ...
    clickedCallbacks.foreach(f => f())
  }
}

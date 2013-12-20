// code-examples/BasicOOP/ui/radio-button-callbacks.scala

package basicoop.ui
import traits.ui._

/**
 * Button with two states, on or off, like an old-style,
 * channel-selection button on a radio.
 */
class RadioButtonWithCallbacks(
  var on: Boolean, label: String, clickedCallbacks: List[() => Unit])
      extends ButtonWithCallbacks(label, clickedCallbacks) {

  def this(on: Boolean, label: String, clickedCallback: () => Unit) =
      this(on, label, List(clickedCallback))

  def this(on: Boolean, label: String) = this(on, label, Nil)
}

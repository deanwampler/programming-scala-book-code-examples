// code-examples/AdvOOP/objects/radio-button.scala

package advoop.objects

/**
 * Button with two states, on or off, like an old-style, 
 * channel-selection botton on a radio.
 */
class RadioButton(val on: Boolean, label: String) extends Button(label)

object RadioButton {
  def unapply(button: RadioButton) = Some((button.on, button.label))
                 // equivalent to: = Some(Pair(button.on, button.label))
}

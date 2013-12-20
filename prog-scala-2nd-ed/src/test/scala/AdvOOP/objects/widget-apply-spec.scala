// code-examples/AdvOOP/objects/widget-apply-spec.scala

package objects
import org.specs2.mutable._

object WidgetApplySpec extends Specification {
  "Widget.apply with a valid widget specification string" should {
    "return a widget instance with the correct fields set" in {
      Widget("(button: label=click me, (Widget))") match {
        case Some(w) => w match {
          case b:Button => b.label mustEqual "click me"
          case x => fail(x.toString())
        }
        case None => fail("None returned.")
      }
      Widget("(textfield: text=This is text, (Widget))") match {
        case Some(w) => w match {
          case tf:TextField => tf.text mustEqual "This is text"
          case x => fail(x.toString())
        }
        case None => fail("None returned.")
      }
    }
  }
  "Widget.apply with an invalid specification string" should {
    "return None" in {
      Widget("(button: , (Widget)") mustEqual None
    }
  }
}

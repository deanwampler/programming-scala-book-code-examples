// code-examples/AdvOOP/objects/widget-apply-spec.scala

package objects
import org.scalatest.{ FunSpec, ShouldMatchers } 


class WidgetApplySpec extends FunSpec with ShouldMatchers {
  describe "Widget.apply with a valid widget specification string" {
    it "return a widget instance with the correct fields set" {
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
  describe "Widget.apply with an invalid specification string" {
    it "return None" {
      Widget("(button: , (Widget)") mustEqual None
    }
  }
}

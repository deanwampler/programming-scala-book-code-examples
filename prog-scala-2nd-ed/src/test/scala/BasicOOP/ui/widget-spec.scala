// code-examples/BasicOOP/ui/widget-spec.scala
package ui

import org.specs2.mutable._ 

object WidgetSpec extends Specification { 
    "A new Widget#Properties" should { 
        "be empty" in { 
            val widget = new Widget {}
            widget.properties.size mustEqual 0
        }
    }
    "After updating a property, a Widget#Properties" should { 
        "have the element" in { 
            val widget = new Widget {}
            widget.properties.size mustEqual 0
            widget.properties.update("Visible", true)
            widget.properties.size mustEqual 1
            widget.properties.get("Visible") mustEqual Some(true)
        }
    }
}

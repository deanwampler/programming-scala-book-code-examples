// code-examples/BasicOOP/ui/widget-spec.scala
package ui

import org.scalatest.{ FunSpec, ShouldMatchers } 

class WidgetSpec extends FunSpec with ShouldMatchers { 
    describe ("A new Widget#Properties") { 
        it ("be empty") { 
            val widget = new Widget {}
            widget.properties.size mustEqual 0
        }
    }
    describe ("After updating a property, a Widget#Properties") { 
        it ("have the element") { 
            val widget = new Widget {}
            widget.properties.size mustEqual 0
            widget.properties.update("Visible", true)
            widget.properties.size mustEqual 1
            widget.properties.get("Visible") mustEqual Some(true)
        }
    }
}

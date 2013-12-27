// src/test/scala/BasicOOP/ui/widget-spec.scala
package basicoop.ui

import org.scalatest.{ FunSpec, ShouldMatchers } 

class WidgetSpec extends FunSpec with ShouldMatchers { 
    describe ("A new Widget#Properties") { 
        it ("be empty") { 
            val widget = new Widget {}
            widget.properties.size shouldEqual 0
        }
    }
    describe ("After updating a property, a Widget#Properties") { 
        it ("have the element") { 
            val widget = new Widget {}
            widget.properties.size shouldEqual 0
            widget.properties.update("Visible", true)
            widget.properties.size shouldEqual 1
            widget.properties.get("Visible") shouldEqual Some(true)
        }
    }
}

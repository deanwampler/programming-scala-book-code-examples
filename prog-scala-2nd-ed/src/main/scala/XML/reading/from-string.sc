// code-examples/XML/reading/from-string-script.scala

import scala.xml._

val someXMLInAString = """
<sammich>
  <bread>wheat</bread>
  <meat>salami</meat>
  <condiments>
    <condiment expired="true">mayo</condiment>
    <condiment expired="false">mustard</condiment>
  </condiments>
</sammich>
"""

val someXML = XML.loadString(someXMLInAString)
assert(someXML.isInstanceOf[scala.xml.Elem])
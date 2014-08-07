// src/main/scala/progscala2/xml/reading/from-string.sc

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

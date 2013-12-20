// code-examples/XML/reading/pattern-matching-script.scala

import scala.xml._

val someXML =
<sammich>
  <bread>wheat</bread>
  <meat>salami</meat>
  <condiments>
    <condiment expired="true">mayo</condiment>
    <condiment expired="false">mustard</condiment>
  </condiments>
</sammich>

someXML match {
  case <sammich>{ingredients @ _*}</sammich> => {
    for (cond @ <condiments>{_*}</condiments> <- ingredients)
      println("condiments: " + cond.text)
  }
}
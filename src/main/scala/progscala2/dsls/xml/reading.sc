// src/main/scala/progscala2/dsls/xml/reading.sc
import scala.xml._                                                   // <1>

val xmlAsString = "<sammich>...</sammich>"                           // <2>
val xml1 = XML.loadString(xmlAsString)

val xml2 =                                                           // <3>
<sammich>
  <bread>wheat</bread>
  <meat>salami</meat>
  <condiments>
    <condiment expired="true">mayo</condiment>
    <condiment expired="false">mustard</condiment>
  </condiments>
</sammich>

for {                                                                // <4>
  condiment <- (xml2 \\ "condiment")
  if (condiment \ "@expired").text == "true"
} println(s"the ${condiment.text} has expired!")

def isExpired(condiment: Node): String =                             // <5>
  condiment.attribute("expired") match {
    case Some(Nil) | None => "unknown!"
    case Some(nodes) => nodes.head.text
  }

xml2 match {                                                         // <6>
  case <sammich>{ingredients @ _*}</sammich> => {
    for {
      condiments @ <condiments>{_*}</condiments> <- ingredients
      cond <- condiments \ "condiment"
    } println(s"  condiment: ${cond.text} is expired? ${isExpired(cond)}")
  }
}

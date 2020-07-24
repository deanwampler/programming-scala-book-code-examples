// src/script/scala/progscala3/implicits/ToJSONTypeClasses.scala
import scala.language.implicitConversions

trait ToJSON {                                                        // <1>
  def toJSON(level: Int = 0): String

  val INDENTATION = "  "
  def indentation(level: Int): (String,String) =
    (INDENTATION * level, INDENTATION * (level+1))
}

implicit final class AddressToJSON(address: Address) extends ToJSON { // <2>
  def toJSON(level: Int = 0): String = {
    val (outdent, indent) = indentation(level)
    s"""{
      |${indent}"street": "${address.street}",
      |${indent}"city":   "${address.city}"
      |$outdent}""".stripMargin
  }
}

implicit final class PersonToJSON(person: Person) extends ToJSON {    // <3>
  def toJSON(level: Int = 0): String = {
    val (outdent, indent) = indentation(level)
    s"""{
      |${indent}"name":    "${person.name}",
      |${indent}"address": ${person.address.toJSON(level + 1)}
      |$outdent}""".stripMargin
  }
}

address.toJSON()
person.toJSON()

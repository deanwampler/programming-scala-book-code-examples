// src/script/scala/progscala3/implicits/ToJSONNewTypeClasses.scala

trait ToJSON3[T] {                                             // <1>
  def toJSON3(level: Int): String = "{}"

  val INDENTATION = "  "
  def indentation(level: Int): (String,String) =
    (INDENTATION * level, INDENTATION * (level+1))
}

given ToJSON3[Address]:                                      // <2>
  def (address: Address) toJSON3(level: Int): String = {
    val (outdent, indent) = indentation(level)
    s"""{
      |${indent}"street": "${address.street}",
      |${indent}"city":   "${address.city}"
      |$outdent}""".stripMargin
  }

given ToJSON3[Person] {                                      // <3>
  def (person: Person) toJSON3(level: Int): String = {
    val (outdent, indent) = indentation(level)
    s"""{
      |${indent}"name":    "${person.name}",
      |${indent}"address": ${person.address.toJSON(level + 1)}
      |$outdent}""".stripMargin
  }
}

val address = Address("1 Scala Lane", "Anytown")
val person = Person("Buck Trends", address)

address.toJSON3(0)
person.toJSON3(0)

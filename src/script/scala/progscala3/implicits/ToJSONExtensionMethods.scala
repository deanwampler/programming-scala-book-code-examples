// src/script/scala/progscala3/implicits/ToJSONExtensionMethods.scala
object ToJSONUtil {                                                // <1>
  val INDENTATION = "  "
  def indentation(level: Int): (String,String) =
    (INDENTATION * level, INDENTATION * (level+1))
}

def (address: Address) toJSON2(level: Int): String = {             // <2>
  val (outdent, indent) = ToJSONUtil.indentation(level)
  s"""{
    |${indent}"street": "${address.street}",
    |${indent}"city":   "${address.city}"
    |$outdent}""".stripMargin
}

def (person: Person) toJSON2(level: Int): String = {
  val (outdent, indent) = ToJSONUtil.indentation(level)
  s"""{
    |${indent}"name":    "${person.name}",
    |${indent}"address": ${person.address.toJSON(level + 1)}
    |$outdent}""".stripMargin
}

val address = Address("1 Scala Lane", "Anytown")
val person = Person("Buck Trends", address)

address.toJSON2(0)
person.toJSON2(0)

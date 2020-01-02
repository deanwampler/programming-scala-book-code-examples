// src/main/scala/progscala3/implicits/toJSON-type-class.sc

case class Address(street: String, city: String)
case class Person(name: String, address: Address)

trait ToJSON {
  def toJSON(level: Int = 0): String

  val INDENTATION = "  "
  def indentation(level: Int = 0): (String,String) = 
    (INDENTATION * level, INDENTATION * (level+1))
}

implicit class AddressToJSON(address: Address) extends ToJSON {
  def toJSON(level: Int = 0): String = {
    val (outdent, indent) = indentation(level)
    s"""{
      |${indent}"street": "${address.street}", 
      |${indent}"city":   "${address.city}"
      |$outdent}""".stripMargin
  }
}

implicit class PersonToJSON(person: Person) extends ToJSON {
  def toJSON(level: Int = 0): String = {
    val (outdent, indent) = indentation(level)
    s"""{
      |${indent}"name":    "${person.name}", 
      |${indent}"address": ${person.address.toJSON(level + 1)} 
      |$outdent}""".stripMargin
  }
}

val a = Address("1 Scala Lane", "Anytown")
val p = Person("Buck Trends", a)

def ns(s: String) = s.replaceAll("\\s+", "")  // remove white space
assert(ns(a.toJSON()) == ns("""{
  |  "street": "1 Scala Lane",
  |  "city":   "Anytown"
  |}""".stripMargin))
assert(ns(p.toJSON()) == ns("""{
  |  "name":    "Buck Trends",
  |  "address": {
  |    "street": "1 Scala Lane",
  |    "city":   "Anytown"
  |  }
  |}""".stripMargin))

// src/main/scala/Implicits/toJSON-type-class.sc

case class Address(street: String, city: String, state: String, zip: String)
case class Person(name: String, age: Int, address: Address)

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
      |${indent}"city":   "${address.city}", 
      |${indent}"state":  "${address.state}", 
      |${indent}"zip":    "${address.zip}" 
      |$outdent}""".stripMargin
  }
}

implicit class PersonToJSON(person: Person) extends ToJSON {
  def toJSON(level: Int = 0): String = {
    val (outdent, indent) = indentation(level)
    s"""{
      |${indent}"name":    "${person.name}", 
      |${indent}"age":     "${person.age}", 
      |${indent}"address": ${person.address.toJSON(level + 1)} 
      |$outdent}""".stripMargin
  }
}

val a = Address("1 Scala Lane", "Anytown", "CA", "98765")
val p = Person("Buck Trends", 29, a)

println(a.toJSON())
println()
println(p.toJSON())

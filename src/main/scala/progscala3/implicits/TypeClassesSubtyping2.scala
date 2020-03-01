// src/main/scala/progscala3/implicits/TypeClassesSubtyping2.scala
// Adapted from: src/main/scala/progscala3/implicits/toJSON-type-class.sc
// This program does not appear in the book.

package progscala3.implicits

object TypeClassesSubtyping2 {

  sealed trait DomainConcept
  case class Address(street: String, city: String, state: String, zip: String)
    extends DomainConcept
  case class Person(name: String, age: Int, address: Address)
    extends DomainConcept

  trait ToJSON[+T] {
    def toJSON(level: Int = 0): String

    val INDENTATION = "  "
    def indentation(level: Int = 0): (String,String) =
      (INDENTATION * level, INDENTATION * (level+1))
  }

  implicit class AddressToJSON(address: Address) extends ToJSON[Address] {
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

  implicit class PersonToJSON(person: Person) extends ToJSON[Person] {
    def toJSON(level: Int = 0): String = {
      val (outdent, indent) = indentation(level)
      s"""{
        |${indent}"name":    "${person.name}",
        |${indent}"age":     "${person.age}",
        |${indent}"address": ${new AddressToJSON(person.address).toJSON(level+1)}
        |$outdent}""".stripMargin
    }
  }

  implicit class DomainConceptToJSON(dc: DomainConcept) extends ToJSON[DomainConcept] {
    def toJSON(level: Int = 0): String = {
      val (outdent, indent) = indentation(level)
      s"""{
        |${indent}"error":    "DomainConceptToJSON was used instead of more specific conversion!"
        |$outdent}""".stripMargin
    }
  }

  def main(args: Array[String]): Unit = {
    val a = Address("1 Scala Lane", "Anytown", "CA", "98765")

    // We want to use this list:
    val list1 = List(a, Person("Buck Trends", 29, a))

    // But this attempt to convert to JSON, and other variations, don't work.
    // Here, we get "error: value toJSON is not a member of Product with
    // java.io.Serializable"
    // println(list1.map(_.toJSON()))

    // This works, but it's ugly and the list has the *ToJSON objects, not
    // the original Address and Person:
    val list2: List[ToJSON[Any]] = List(a, Person("Buck Trends", 29, a))
    println(list2.map((x: ToJSON[Any]) => x.toJSON()))

    // So, does this work?
    // implicit class ToJSONs[+T : ToJSON](seq: Seq[T]) {
    //   def apply(): Seq[String] =
    //     seq map (t => implicitly[ToJSON[T]].toJSON())
    // }
    // NO: It complains: "error: value toJSON is not a member of
    // List[Product with java.io.Serializable]"
    // println(list1.toJSONs())

    // Does this ugly hack fix it?
    // implicit class AnyToJSON(x: Any) extends ToJSON[Any] {
    //   def toJSON(level: Int = 0): String = x match {
    //     case person: Person   => new PersonToJSON(person).toJSON(level)
    //     case address: Address => new AddressToJSON(address).toJSON(level)
    //     case other => throw new UnsupportedOperationException(
    //       s"No toJSON type class instance is available for $other")
    //   }
    // }
    // NO: it can't handle a list:
    // AnyToJSON(list1).toJSON()

    // This sort of works...
    val success = list1 map ((dc: DomainConcept) => dc.toJSON())
    println(success)
    // But it only worked because we used this expression in the PersonToJSON,
    // ${new AddressToJSON(person.address).toJSON(level+1)}, rather than
    // ${person.address.toJSON(level+1)}. For the later, the GodToJSON isn't
    // "found" and the "other" exception is thrown when we try to process the
    // Person instance.

    val expected = List("""{
        "street": "1 Scala Lane",
        "city":   "Anytown",
        "state":  "CA",
        "zip":    "98765"
      }""", """{
        "name":    "Buck Trends",
        "age":     "29",
        "address": {
          "street": "1 Scala Lane",
          "city":   "Anytown",
          "state":  "CA",
          "zip":    "98765"
        }
      }""")

    // But actually, the way we mapped over list1 causes the default DomainConceptToJSON
    // to be used, which is not what we want.
    // That list could be rewritten with an HList, so the type of each list element is
    // correctly handled.
    assert( // ignore whitespace
      success.map(_.replaceAll("\\s+", "")) ==
      expected.map(_.replaceAll("\\s+", "")))
  }
}


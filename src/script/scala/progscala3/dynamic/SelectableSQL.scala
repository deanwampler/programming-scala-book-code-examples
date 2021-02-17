// tag::definitions[]
// src/script/scala/progscala3/dynamic/SelectableSQL.scala

import reflect.ClassTag
import collection.mutable.HashMap as HMap

object SQL:
  open class Record(elems: (String, Any)*) extends Selectable:
    private val fields = HMap.from(elems.toMap)                      // <1>

    def selectDynamic(name: String): Any = fields(name)              // <2>

    def applyDynamic(                                                // <3>
        operation: String, paramTypes: ClassTag[?]*)(args: Any*): Any =
      val fieldName = operation.drop("update".length)  // remove prefix
      val fname = fieldName.head.toLower +: fieldName.tail           // <4>
      fields += fname -> args.head

    override def toString: String = s"Record($fields)"

type Person = SQL.Record {
  val name: String                                                   // <5>
  val age: Int
  def updateName(newName: String): Unit                              // <6>
  def updateAge(newAge: Int): Unit
}
// end::definitions[]

// tag::person[]
val person = SQL.Record(                                             // <1>
  "name" -> "Buck Trends", "age" -> 29,
  "famous" -> false).asInstanceOf[Person]
person.name
person.age
person.selectDynamic("name")                                         // <2>
person.selectDynamic("age")

person.famous                     // ERROR
person.selectDynamic("famous")
// end::person[]

// tag::updates[]
person.updateName("Dean Wampler")
person.updateAge(30)
person

person.updateFamous(true)         // ERROR
person.applyDynamic("updateFamous", summon[ClassTag[Boolean]])(true)
// end::updates[]

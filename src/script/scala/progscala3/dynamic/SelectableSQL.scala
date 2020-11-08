// tag::definitions[]
// src/script/scala/progscala3/dynamic/SelectableSQL.scala

import reflect.ClassTag
import reflect.Selectable.reflectiveSelectable
import collection.mutable.{HashMap => HMap}

object SQL:                                                          // <1>
  open class Record(elems: (String, Any)*) extends reflect.Selectable:
    private val fields = HMap.from(elems.toMap)                      // <2>

    override def selectDynamic(name: String): Any = fields(name)     // <3>

    override def applyDynamic(                                       // <4>
        operation: String, paramTypes: ClassTag[?]*)(args: Any*): Any = {
      val fieldName = operation.drop("update".length)  // remove prefix
      val fname = fieldName.head.toLower +: fieldName.tail           // <5>
      fields += fname -> args.head
    }

    override def toString: String = s"Record($fields)"

type Person = SQL.Record {
  val name: String                                                   // <6>
  val age: Int
  def updateName(newName: String): Unit                              // <7>
  def updateAge(newAge: Int): Unit
}
// end::definitions[]

// tag::person[]
val person = new SQL.Record(                                  // <1>
  "name" -> "Buck Trends", "age" -> 29).asInstanceOf[Person]
person.name
person.age
person.selectDynamic("name")                                  // <2>
// end::person[]

// tag::updates[]
person.updateName("Dean Wampler")
person.updateAge(30)
person
// end::updates[]

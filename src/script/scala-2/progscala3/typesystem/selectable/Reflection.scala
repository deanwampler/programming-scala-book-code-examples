// src/script/scala-2/progscala3/typesystem/selectable/Reflection.scala
// Mentioned, but not shown in the book. This shows a Scala 2 alternative to
// .../scala/progscala3/typesystem/selectable/Selectable.scala
// using structural typing.
import scala.language.reflectiveCalls   // Required import.
trait Record {  // Doesn't extend any other trait, like Scala 3's reflect.Selectable
  def id: Long
}

val persons = Seq("Dean" -> 29, "Dean" -> 29, "Dean" -> 30, "Fred" -> 30)
  .map{ case (name1, age1) =>
    new Record {
      def id: Long     = 0L         // Use the same value to verify comparisons work below
      val name: String = name1
      def age: Int     = age1
    }
}

persons.map(p => s"<${p.id}, ${p.name}, ${p.age}>")
// Comparisons are by instance location, not field values!!
assert(persons(0) == persons(0))
assert(persons(0) != persons(1))  // NOTE: Same field values, but different instances!!
assert(persons(0) != persons(2))
assert(persons(0) != persons(3))

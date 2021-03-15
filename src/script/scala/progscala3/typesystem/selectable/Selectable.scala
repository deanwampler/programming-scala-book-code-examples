// src/script/scala/progscala3/typesystem/selectable/Selectable.scala

trait Record extends reflect.Selectable:                        // <1>
  def id: Long      // Id of the record in the database

val persons = Seq("Dean" -> 29, "Dean" -> 29, "Dean" -> 30, "Fred" -> 30)
  .map { case (name1, age1) =>                                  // <2>
    new Record:                                                 // <3>
      def id: Long     = 0L
      val name: String = name1                                  // <4>
      def age: Int     = age1
}

persons.map(p => s"<${p.id}, ${p.name}, ${p.age}>")
assert(persons(0) == persons(0))
assert(persons(0) != persons(1))                                // <5>
assert(persons(0) != persons(2))
assert(persons(0) != persons(3))

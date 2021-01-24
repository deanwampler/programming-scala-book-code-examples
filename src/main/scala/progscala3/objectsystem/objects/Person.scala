// src/main/scala/progscala3/objectsystem/objects/Person.scala
package progscala3.objectsystem.objects

class Person(val name: String, val age: Int):
  override def toString = "name: " + name + ", age: " + age

object Person:
  def apply(name: String, age: Int) = new Person(name, age)
  def unapply(person: Person) = Some((person.name, person.age))

@main def TryPerson(name: String, age: Int) =
  // Test the constructor...
  val person = Person(name, age)
  assert(person.name == name)
  assert(person.age  == age)


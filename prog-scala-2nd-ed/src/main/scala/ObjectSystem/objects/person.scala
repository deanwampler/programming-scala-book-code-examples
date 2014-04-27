// src/main/scala/ObjectSystem/objects/person.scala

package oop.objects

class Person(val name: String, val age: Int) {
  override def toString = "name: " + name + ", age: " + age
}

object Person {
  def apply(name: String, age: Int) = new Person(name, age)
  def unapply(person: Person) = Some((person.name, person.age))
  
  def main(args: Array[String]) = {
    // Test the constructor...
    val person = new Person("Buck Trends", 18)
    assert(person.name == "Buck Trends")
    assert(person.age  == 21)
  }
}

object PersonTest {
  def main(args: Array[String]) = Person.main(args)
}

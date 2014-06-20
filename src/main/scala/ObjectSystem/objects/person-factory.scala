// src/main/scala/ObjectSystem/objects/person-factory.scala

package oop.objects

object PersonFactory {
  def make(name: String, age: Int) = new Person(name, age)
}

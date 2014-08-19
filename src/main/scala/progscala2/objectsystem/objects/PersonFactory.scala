// src/main/scala/progscala2/objectsystem/objects/PersonFactory.scala
package progscala2.objectsystem.objects

object PersonFactory {
  def make(name: String, age: Int) = new Person(name, age)
}

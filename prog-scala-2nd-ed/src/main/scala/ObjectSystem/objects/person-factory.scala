// src/main/scala/AdvOOP/objects/person-factory.scala

package advoop.objects

object PersonFactory {
  def make(name: String, age: Int) = new Person(name, age)
}

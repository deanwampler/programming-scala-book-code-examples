// src/main/scala/progscala3/basicoop/people/PersonAuxConstructors.scala
package progscala3.basicoop.people

/** 
 * Define auxiliary constructors. While this is common in other
 * OO languages, like Java, it is not common in Scala code, where
 * default arguments and case object `apply` methods are used instead.
 */
case class PersonAuxConstructors(
  name: String, age: Option[Int], address: Option[Address]) {        // <3>

  def this(name: String) = this(name, None, None)                    // <4>

  def this(name: String, age: Int) = this(name, Some(age), None)

  def this(name: String, age: Int, address: Address) =
    this(name, Some(age), Some(address))

  def this(name: String, address: Address) = this(name, None, Some(address))
}

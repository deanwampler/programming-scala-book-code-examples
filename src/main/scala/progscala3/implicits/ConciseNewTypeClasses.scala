// src/main/scala/progscala3/implicits/ConciseNewTypeClasses.scala
package progscala3.implicits

import scala.language.implicitConversions

class Loud(s: String):
  def loud: String = s.toUpperCase

object Loud:
  given Conversion[String, Loud] = s => new Loud(s)             // <1>
  // Equivalent to:
  // given Conversion[String, Loud]:
  //   def apply(s: String) = new Loud(s)

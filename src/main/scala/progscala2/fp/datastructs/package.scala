// src/main/scala/progscala2/fp/datastructs/package.scala
package progscala2.fp
package object datastructs {
  type Seq[+A] = scala.collection.immutable.Seq[A]
  val Seq = scala.collection.immutable.Seq
}

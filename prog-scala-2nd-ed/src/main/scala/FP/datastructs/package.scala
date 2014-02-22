// src/main/scala/FP/datastructs/package.scala

package FP
package object datastructs {
  type Seq[+A] = scala.collection.immutable.Seq[A]
  val Seq = scala.collection.immutable.Seq
}

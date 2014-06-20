// src/main/scala/FP/datastructs/package.scala

package fp
package object datastructs {
  type Seq[+A] = scala.collection.immutable.Seq[A]
  val Seq = scala.collection.immutable.Seq
}

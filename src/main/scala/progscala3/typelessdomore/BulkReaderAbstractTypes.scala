// src/main/scala/progscala3/typelessdomore/BulkReaderAbstractTypes.scala
package progscala3.typelessdomore
import scala.io.Source

abstract class BulkReader:
  type In                                                            // <1>
  /** The source of data to read. */
  val source: In
  /** Read source and return a sequence of Strings */
  def read: Seq[String]

case class StringBulkReader(source: String) extends BulkReader:      // <2>
  type In = String
  def read: Seq[String] = Seq(source)

case class FileBulkReader(source: Source) extends BulkReader:        // <3>
  type In = Source
  def read: Seq[String] = source.getLines.toVector

// src/script/scala/progscala3/typelessdomore/ParameterizedTypes.scala
import java.io._

abstract class BulkReader[In]:
  val source: In
  def read: String

class StringBulkReader(val source: String) extends BulkReader[String]:
  def read = source

class FileBulkReader(val source: File) extends BulkReader[File]:
  def read =
    val in = new BufferedInputStream(new FileInputStream(source))
    val numBytes = in.available()
    val bytes = new Array[Byte](numBytes)
    in.read(bytes, 0, numBytes)
    new String(bytes)

val sbb = new StringBulkReader("Hello Scala!")
println(sbb.read)

val path =
  "src/script/scala/progscala3/typelessdomore/ParameterizedTypes.scala"
val fbb = new FileBulkReader(new File(path))
println(fbb.read)

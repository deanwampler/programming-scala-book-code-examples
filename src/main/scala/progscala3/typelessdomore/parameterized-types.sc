// src/main/scala/progscala3/typelessdomore/parameterized-types.sc
import java.io._

abstract class BulkReader[In] {
  val source: In
  def read: String
}

class StringBulkReader(val source: String) extends BulkReader[String] {
  def read = source
}

class FileBulkReader(val source: File) extends BulkReader[File] {
  def read = {
    val in = new BufferedInputStream(new FileInputStream(source))
    val numBytes = in.available()
    val bytes = new Array[Byte](numBytes)
    in.read(bytes, 0, numBytes)
    new String(bytes)
  }
}

val sbb = new StringBulkReader("Hello Scala!")
println(sbb.read)

// Assumes the current directory is the project root:
val path = "src/main/scala/progscala3/typelessdomore/parameterized-types.sc"
val fbb = new FileBulkReader(new File(path))
println(fbb.read)

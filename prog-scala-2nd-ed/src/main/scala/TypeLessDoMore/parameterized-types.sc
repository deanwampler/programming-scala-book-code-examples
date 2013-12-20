// code-examples/TypeLessDoMore/parameterized-types-script.scala
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

println( new StringBulkReader("Hello Scala!").read )
println( new FileBulkReader(new File("parameterized-types-script.scala")).read )

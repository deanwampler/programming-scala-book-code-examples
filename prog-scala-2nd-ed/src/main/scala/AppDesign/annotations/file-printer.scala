// src/main/scala/AppDesign/annotations/file-printer.scala

package appdesign.annotations
import java.io._

class FilePrinter(val file: File) {

  @throws(classOf[IOException])
  def print() = {
    var reader: LineNumberReader = null
    try {
      reader = new LineNumberReader(new FileReader(file))
      loop(reader)
    } finally {
      if (reader != null)
        reader.close
    }
  }
      
  private def loop(reader: LineNumberReader): Unit = {
    val line = reader.readLine()
    if (line != null) {
      "%3d: %s\n".format(reader.getLineNumber, line)
      loop(reader)
    }
  }
}

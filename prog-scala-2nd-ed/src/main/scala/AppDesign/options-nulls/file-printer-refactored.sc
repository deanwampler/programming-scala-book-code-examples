// code-examples/AppDesign/options-nulls/file-printer-refactored-script.scala

import java.io._

class ScalaIOException(cause: Throwable) extends RuntimeException(cause)

class ScalaLineNumberReader(in: Reader) extends LineNumberReader(in) {
  def inputLine() = readLine() match {
    case null => None
    case line => Some(line)
  }
}

object ScalaLineNumberReader {
  def apply(file: File) = try {
     new ScalaLineNumberReader(new FileReader(file))
  } catch {
    case ex: IOException => throw new ScalaIOException(ex)
  }
}

class FilePrinter(val file: File) {
  def print() = {
    val reader = ScalaLineNumberReader(file)
    try {
      loop(reader)
    } finally {
      if (reader != null)
        reader.close
    }
  }
  
  private def loop(reader: ScalaLineNumberReader): Unit = {
    reader.inputLine() match {
      case None => 
      case Some(line) => {
        format("%3d: %s\n", reader.getLineNumber, line)
        loop(reader)
      }
    }
  }
}

// Process the command-line arguments (file names):
args.foreach { fileName =>
  new FilePrinter(new File(fileName)).print();
}
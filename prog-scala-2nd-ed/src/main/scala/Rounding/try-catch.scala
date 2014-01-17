// src/main/scala/Rounding/try-catch.scala

package rounding

object TryCatch {
  /** Usage: scala TryCatch filename1 filename2 ... */
  def main(args: Array[String]) = {
    args foreach (arg => countLines(arg))
  }

  import scala.io.Source

  def countLines(fileName: String) = {
    println()  // Add a blank line for legibility
    var source: Option[Source] = None
    try {
      source = Some(Source.fromFile(fileName))
      // Calling get is safe here, but calling getLines is a bit inefficient.
      val size = source.get.getLines.size
      println(s"file $fileName has $size lines")
      if (size > 20) throw new RuntimeException("Big file!")
    } catch {
      case ex: Throwable => println(s"Exception! $ex")
    } finally {
      for (s <- source) {
        println(s"Closing $fileName...")
        s.close
      }
    }
  }
}

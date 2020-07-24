// src/main/scala/progscala3/rounding/TryCatch.scala
package progscala3.rounding

object TryCatch {
  /** Usage: scala rounding.TryCatch filename1 filename2 ... */
  def main(args: Array[String]): Unit = {
    args foreach (arg => countLines(arg))                            // <1>
  }

  import scala.io.Source                                             // <2>
  import scala.util.control.NonFatal

  def countLines(fileName: String) = {                               // <3>
    var source: Option[Source] = None                                // <4>
    try                                                              // <5>
      source = Some(Source.fromFile(fileName))                       // <6>
      val size = source.get.getLines.size
      println(s"file $fileName has $size lines")
    catch
      case NonFatal(ex) => println(s"Non fatal exception! $ex")      // <7>
    finally
      for s <- source do                                             // <8>
        println(s"Closing $fileName...")
        s.close
  }

  def countLines2(fileName: String) = {                              // <9>
    println()  // Add a blank line for legibility
    var source: Option[Source] = None
    try {
      source = Some(Source.fromFile(fileName))
      val size = source.get.getLines.size
      println(s"file $fileName has $size lines")
    } catch {
      case NonFatal(ex) => println(s"Non fatal exception! $ex")
    } finally {
      for s <- source do {
        println(s"Closing $fileName...")
        s.close
      }
    }
  }
}

// src/main/scala/progscala3/rounding/TryCatch.scala
package progscala3.rounding
import scala.io.Source                                          // <1>
import scala.util.control.NonFatal

/** Usage: scala rounding.TryCatch filename1 filename2 ... */
@main def TryCatch(fileNames: String*) =                        // <2>
  fileNames.foreach { fileName =>
    var source: Option[Source] = None                           // <3>
    try                                                         // <4>
      source = Some(Source.fromFile(fileName))                  // <5>
      val size = source.get.getLines.size
      println(s"file $fileName has $size lines")
    catch
      case NonFatal(ex) => println(s"Non fatal exception! $ex") // <6>
    finally
      for s <- source do                                        // <7>
        println(s"Closing $fileName...")
        s.close
  }

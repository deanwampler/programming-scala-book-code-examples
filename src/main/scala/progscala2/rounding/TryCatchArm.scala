// src/main/scala/progscala2/rounding/TryCatchArm.scala
package progscala2.rounding
import scala.language.reflectiveCalls
import scala.util.control.NonFatal

// DeanW (Dec. 21, 2015): Refined the implementation and the usage
// example below to more clearly indicate the handling of the returned
// object of type T.
object manage {
  def apply[R <: { def close():Unit }, T](resource: => R)(f: R => T): T = {
    var res: Option[R] = None
    try {
      res = Some(resource)         // Only reference "resource" once!!
      f(res.get)                   // Return the T instance
    } catch {
      case NonFatal(ex) =>
        println(s"manage.apply(): Non fatal exception! $ex")
        throw ex
    } finally {
      if (res != None) {
        println(s"Closing resource...")
        res.get.close
      }
    }
  }
}

object TryCatchARM {
  /** Usage: scala rounding.TryCatch filename1 filename2 ... */
  def main(args: Array[String]) = {
    val sizes = args map (arg => returnFileLength(arg))
    println("Returned sizes: " + (sizes.mkString(", ")))
  }

  import scala.io.Source

  def returnFileLength(fileName: String): Int = {
    println()  // Add a blank line for legibility
    manage(Source.fromFile(fileName)) { source =>
      val size = source.getLines.size
      println(s"file $fileName has $size lines")
      if (size > 200) throw new RuntimeException(s"Big file: $fileName!")
      size
    }
  }
}

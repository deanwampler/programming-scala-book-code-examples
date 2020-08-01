// src/main/scala/progscala3/rounding/TryCatchArm.scala
package progscala3.rounding
import scala.language.reflectiveCalls
import reflect.Selectable.reflectiveSelectable
import scala.util.control.NonFatal
import scala.io.Source

object manage:
  def apply[R <: { def close():Unit }, T](resource: => R)(f: R => T): T =
    var res: Option[R] = None
    try
      res = Some(resource)         // Only reference "resource" once!!
      f(res.get)                   // Return the T instance
    catch
      case NonFatal(ex) =>
        println(s"manage.apply(): Non fatal exception! $ex")
        throw ex
    finally
      if res != None then
        println(s"Closing resource...")
        res.get.close()

object TryCatchARM:
  /** Usage: scala rounding.TryCatch filename1 filename2 ... */
  def main(args: Array[String]): Unit =
    val sizes = args map { arg =>
      try
        val size = manage(Source.fromFile(arg)) { source =>
          source.getLines.size
        }
        println(s"file $arg has $size lines")
        size
      catch
        case NonFatal(ex) =>
          println(s"caught $ex")
          -1
    }
    println("Returned sizes: " + (sizes.mkString(", ")))

  def returnFileLength(fileName: String): Int =
    manage(Source.fromFile(fileName)) { source =>
      source.getLines.size
    }

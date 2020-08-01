// src/main/scala/progscala3/rounding/FileSizes.scala
package progscala3.rounding

import scala.util.Using
import scala.io.Source

object FileSizes:
  /** Usage: scala rounding.FileSizes filename1 filename2 ... */
  def main(args: Array[String]): Unit =
    val sizes = args map { arg =>
      Using.resource(Source.fromFile(arg)) { source =>
        source.getLines.size
      }
    }
    println(s"Returned sizes: ${sizes.mkString(", ")}")
    println(s"Total size: ${sizes.sum}")

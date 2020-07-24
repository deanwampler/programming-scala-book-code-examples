// src/main/scala/progscala3/rounding/Path.scala
package progscala3.rounding

import scala.annotation.alpha
import java.io.File

case class Path(value: String, separator: String = Path.defaultSeparator) {
  val file = new File(value)

  @alpha("concat") def / (node: String): Path =
    new Path(value + separator + node, separator)
}

object Path {
  val defaultSeparator = sys.props("file.separator")
}

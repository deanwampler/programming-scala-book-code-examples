// src/main/scala/progscala3/rounding/Path.scala
package progscala3.rounding

import scala.annotation.targetName
import java.io.File

case class Path(
    value: String, separator: String = Path.defaultSeparator):  // <1>
  val file = File(value)
  override def toString: String = file.getPath                  // <2>

  @targetName("concat") def / (node: String): Path =            // <3>
    copy(value + separator + node)                              // <4>

  infix def append(node: String): Path = /(node)                // <5>

object Path:
  val defaultSeparator = sys.props("file.separator")

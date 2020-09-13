// src/main/scala/progscala3/implicits/json/ToJSON.scala
package progscala3.implicits.json

trait ToJSON:
  def toJSON(level: Int): String

  protected val INDENTATION = "  "
  protected def indentation(level: Int): (String,String) =
    (INDENTATION * level, INDENTATION * (level+1))

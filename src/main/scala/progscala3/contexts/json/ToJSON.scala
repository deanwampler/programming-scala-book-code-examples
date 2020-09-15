// src/main/scala/progscala3/contexts/json/ToJSON.scala
package progscala3.contexts.json

trait ToJSON[T]:
  extension (t: T) def toJSON(name: String, level: Int): String

  protected val INDENTATION = "  "
  protected def indentation(level: Int): (String,String) =
    (INDENTATION * level, INDENTATION * (level+1))

// src/main/scala/progscala3/forcomps/RemoveBlanks.scala
package progscala3.forcomps

object RemoveBlanks {

  /**
   * Remove blank lines from the specified input file.
   */
  def apply(path: String, compressWhiteSpace: Boolean = false): Seq[String] =
    for
      line <- scala.io.Source.fromFile(path).getLines.toSeq             // <1>
      if line.matches("""^\s*$""") == false                             // <2>
      line2 = if compressWhiteSpace then line.trim.replaceAll("\\s+", " ") // <3>
              else line
    yield line2                                                       // <4>
}

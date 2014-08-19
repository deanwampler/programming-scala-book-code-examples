// src/main/scala/progscala2/forcomps/RemoveBlanks.scala
package progscala2.forcomps

object RemoveBlanks {

  /**
   * Remove blank lines from the specified input file.
   */
  def apply(path: String, compressWhiteSpace: Boolean = false): Seq[String] =
    for {
      line <- scala.io.Source.fromFile(path).getLines.toSeq          // <1>
      if line.matches("""^\s*$""") == false                          // <2>
      line2 = if (compressWhiteSpace) line replaceAll ("\\s+", " ")  // <3>
              else line
    } yield line2                                                    // <4>

  /**
   * Remove blank lines from the specified input files and echo the remaining
   * lines to standard output, one after the other.
   * @param args list of file paths. Prefix each with an optional "-" to
   *             compress remaining whitespace in the file.
   */
  def main(args: Array[String]) = for {
    path2 <- args                                                    // <5>
    (compress, path) = if (path2 startsWith "-") (true, path2.substring(1))
                       else (false, path2)                           // <6>
    line <- apply(path, compress)
  } println(line)                                                    // <7>
}

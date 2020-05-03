// src/main/scala/progscala3/forcomps/RemoveBlanks.scala
package progscala3.forcomps

object RemoveBlanks {

  /**
   * Remove blank lines from the specified input file.
   * Loading a whole file in memory isn't optimal...
   */
  def apply(path: String, compressWhiteSpace: Boolean = false): Seq[String] =
    for
      line <- scala.io.Source.fromFile(path).getLines.toSeq           // <1>
      if line.matches("""^\s*$""") == false                           // <2>
      line2 = if compressWhiteSpace then line.trim.replaceAll("\\s+", " ")
              else line                                               // <3>
    yield line2                                                       // <4>

  def main(args: Array[String]): Unit = {                             // <5>
    args.foreach { file =>
      val (f, nows) =                                                 // <6>
        if file.startsWith("-") then (file.substring(1), true)
        else (file, false)

      println(s"File: $f")
      val seq = RemoveBlanks(f, nows)
      seq.foreach(println)                                            // <7>
    }
  }
}

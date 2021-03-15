// src/main/scala/progscala3/concurrency/process/Process.scala
package progscala3.concurrency.process

import scala.sys.process.*
import java.net.URL
import java.io.File

object DoProcess:

  // Build a process to open a URL, redirect the output to
  // "grep $filter", and append the output to file (not overwrite it).
  def findURL(url: String, filter: String) =
    URL(url) #> s"grep $filter" #>> File(s"$filter.txt")

  // Run ls -l on the file. If it exists, then count the lines.
  def countLines(fileName: String) =
    s"ls -l $fileName" #&& s"wc -l $fileName"

@main def TryProcess =
  import DoProcess.*
  val resultCode = "ls src".!
  assert(resultCode == 0)

  assert(Seq("ls", "src").!! == "main\nscript\ntest\n")

  assert(findURL("https://www.scala-lang.org", "scala").! == 0)
  assert(countLines("scala.txt").! == 0)

  val output = countLines("scala.txt").!!
  println(output)

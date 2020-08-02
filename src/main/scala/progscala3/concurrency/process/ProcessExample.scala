// src/main/scala/progscala3/concurrency/process/ProcessExample.scala
package progscala3.concurrency.process

import scala.sys.process._
import scala.language.implicitConversions
import java.net.URL
import java.io.File

object ProcessExample:

  // Build a process to open a URL, redirect the output to
  // "grep $filter", and append the output to file (not overwrite it).
  def findURL(url: String, filter: String) =
    new URL(url) #> s"grep $filter" #>> new File(s"$filter.txt")

  // Run ls -l on the file. If it exists, then count the lines.
  def countLines(fileName: String) =
    s"ls -1 $fileName" #&& s"wc -l $fileName"

@main def TryProcessExample =
  import ProcessExample._
  val resultCode = "ls src".!
  assert(resultCode == 0)

  assert(Seq("ls", "src").!! == "main\nscript\ntest\n")

  assert(findURL("https://www.scala-lang.org", "scala").! == 0)
  assert(countLines("scala.txt").! == 0)

  val output = countLines("scala.txt").!!
  // Split the output on all non-characters and this is the
  // array you get:
  //   Array("scala", "txt", nstr, "scala", "txt")
  // where "nstr" is the number of lines in "scala.txt":
  val strings = output.split("\\W+")
  assert(strings(2).toInt > 0)

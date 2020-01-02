// src/main/scala/progscala3/concurrency/process/processes.sc
import scala.sys.process._
import java.net.URL
import java.io.File

val resultCode = "ls src".!
assert(resultCode == 0)

assert(Seq("ls", "src").!! == "main\ntest\n")

// Build a process to open a URL, redirect the output to "grep $filter", 
// and append the output to file (not overwrite it).
def findURL(url: String, filter: String) = 
  new URL(url) #> s"grep $filter" #>> new File(s"$filter.txt")

// Run ls -l on the file. If it exists, then count the lines.
def countLines(fileName: String) = s"ls -1 $fileName" #&& s"wc -l $fileName"

assert(findURL("https://www.scala-lang.org", "scala").! == 0)
assert(countLines("scala.txt").! == 0)

val output = countLines("scala.txt").!!
// Split the output on all non-characters and this is the array you get,
// with "nstr" each to the number of lines in "scala.txt":
val Array("scala", "txt", nstr, "scala", "txt") = output.split("\\W+")
assert(nstr.toInt > 0)



// src/script/scala/progscala3/rounding/Uninitialized.scala
import scala.io.Source
import scala.compiletime.uninitialized                          // <1>

case class LineLoader(file: java.io.File):
  private var source: Source = uninitialized                    // <2>
  val lines = try
    source = Source.fromFile("README.md")
    source.getLines.toSeq
  finally
    if source != null then source.close

val ll = LineLoader(java.io.File("README.md"))
assert(ll.lines.take(1) == List("# Programming Scala, 3rd Edition"))

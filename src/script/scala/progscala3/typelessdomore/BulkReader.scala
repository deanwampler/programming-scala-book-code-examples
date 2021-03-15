// src/script/scala/progscala3/typelessdomore/BulkReader.scala
import progscala3.typelessdomore.{StringBulkReader, FileBulkReader}
import scala.io.Source

val strings = StringBulkReader("Hello Scala!").read
val lines = FileBulkReader(Source.fromFile("README.md")).read
lines(0)
lines(2)

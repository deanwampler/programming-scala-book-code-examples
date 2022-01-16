// src/main/scala/progscala3/rounding/saferexceptions/SaferExceptionsNested.scala
// This example is not in the book. It discusses new work, experimental in
// Scala 3.1, to incorporate exception throwing into the type system, but without
// the drawbacks of Java-style checked exceptions.
package progscala3.rounding.saferexceptions
import java.io.{IOException, File}
import scala.annotation.experimental
// Enable safer exceptions
import language.experimental.saferExceptions
// If you enable safer exceptions, use the following annotation as a transition
// feature to allow exceptions to be thrown even when the handler logic isn't in
// place for all cases. Remove this import when handlers have been added!
// import unsafeExceptions.canThrowAny

/**
 * Usage: scala rounding.saferexceptions.SaferExceptionsNested file1 [file2 ...]
 * Note that an object is used, not a `@def main`, because the compiler tells us
 * we need to annotate the SaferExceptions type with `@experimental` and this
 * appears to be the only way to do it. (Also, any methods outside the type that
 * use this feature would also need this annotation.)
 *
 * Here is the output:
 * ```
 * sbt:programming-scala-3rd-ed-code-examples> runMain progscala3.rounding.saferexceptions.SaferExceptionsNested build.sbt badfile
 * [info] compiling 2 Scala sources to .../target/scala-3.1.1-RC2/classes ...
 * [info] running (fork) progscala3.rounding.saferexceptions.SaferExceptionsNested build.sbt badfile
 * [info] file build.sbt (.../build.sbt) has 4434 bytes.
 * [info] file badfile: IOException caught: badfile doesn't exist!
 * [success] Total time: 1 s, completed Jan 16, 2022, 12:32:51 PM
 * ```
 */
@experimental  // Must annotate all the types or methods that use the feature...
object SaferExceptionsNested:
  def main(fileNames: Array[String]): Unit =
    fileNames.foreach { fileName =>
      try
        val file = wrapper(fileName)
        val path = file.getCanonicalPath()
        val size = file.length()
        println(s"file $fileName ($path) has $size bytes.")
      catch
        case ioe: IOException => println(s"file $fileName: IOException caught: ${ioe.getMessage}")
    }

  def wrapper(fileName: String): File throws IOException = openExistingFile(fileName)

  /**
   * `File throws IOException` is equivalent to this definition of `openExisting`:
   * `def openExistingFile(fileName: String)(using CanThrow[IOException]) = ...
   */
  def openExistingFile(fileName: String): File throws IOException =
    val file = new File(fileName)
    if file.exists() == false then throw new IOException(s"$fileName doesn't exist!")
    file

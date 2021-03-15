// src/main/scala/progscala3/appdesign/dbc/Elidable.scala
package progscala3.appdesign.dbc

import scala.annotation.elidable
import scala.annotation.elidable.*

/**
 * This example of the elidable annotation is mentioned in the book, but not shown.
 * Compile outside sbt using the scala compiler:
 * ```
 * $ mkdir -p N
 * $ scalac -d N -Xelide-below N src/main/scala/progscala3/appdesign/dbc/ElidableExample.scala
 * $ scala -classpath N progscala3.appdesign.dbc.TryMyLogger
 * ```
 * for each N: WARNING, INFO, and ASSERTION
 * @note At the time of this writing, the -Xelide-below flag is not available
 * in Scala 3, but it may be added in a subsequent release.
 .*/
object MyLogger:
  @elidable(WARNING)
  def warn(message: String) = println(s"WARNING:        $message")
  @elidable(INFO)
  def info(message: String) = println(s"INFO:           $message")
  @elidable(ASSERTION)
  def assertion(message: String) = println(s"ASSERTION: $message")

@main def TryMyLogger =
  MyLogger.warn("warn")
  MyLogger.info("info")
  MyLogger.assertion("assertion")

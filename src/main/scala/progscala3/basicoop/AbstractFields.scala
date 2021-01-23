// src/main/scala/progscala3/basicoop/AbstractFields.scala
package progscala3.basicoop

trait Logger:
  def loggingLevel: Int                                         // <1>
  def log(message: String): Unit

case class ConsoleLogger(loggingLevel: Int) extends Logger:     // <2>
  def log(message: String): Unit = println(s"$loggingLevel: $message")

// src/main/scala/progscala3/basicoop/AbstractFields.scala
package progscala3.basicoop

trait Logger:
  def level: LoggingLevel                                       // <1>
  def log(message: String): Unit

case class ConsoleLogger(level: LoggingLevel) extends Logger:   // <2>
  def log(message: String): Unit = println(s"$level: $message")

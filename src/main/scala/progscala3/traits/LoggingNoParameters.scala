// src/main/scala/progscala3/traits/LoggingNoParameters.scala
package progscala3.traits.logging

trait LoggerNP:                                                      // <1>
  def level: LoggingLevel
  def log(message: String): Unit

trait ConsoleLoggerNP extends LoggerNP:
  def log(message: String): Unit = println(s"$level: $message")

class ServiceNP(val name: String, val level: LoggingLevel)           // <2>
  extends ConsoleLoggerNP

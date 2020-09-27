// src/main/scala/progscala3/traits/Logging.scala
package progscala3.traits.logging

enum LoggingLevel:
  case DEBUG, INFO, WARN, ERROR, FATAL                               // <1>

trait Logger(val level: LoggingLevel):                               // <2>
  def log(message: String): Unit

trait ConsoleLogger extends Logger:                                  // <3>
  def log(message: String): Unit = println(s"$level: $message")

class Service(val name: String, level: LoggingLevel)                 // <4>
  extends ConsoleLogger with Logger(level)

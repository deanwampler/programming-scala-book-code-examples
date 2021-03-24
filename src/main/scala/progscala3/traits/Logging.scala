// src/main/scala/progscala3/traits/Logging.scala
package progscala3.traits.logging

enum LoggingLevel:
  case Debug, Info, Warn, Error, Fatal                               // <1>

trait Logger(val level: LoggingLevel):                               // <2>
  def log(message: String): Unit

trait ConsoleLogger extends Logger:
  def log(message: String): Unit =
    println(s"${level.toString.toUpperCase}: $message")

class Service(val name: String, level: LoggingLevel)                 // <3>
  extends ConsoleLogger with Logger(level)

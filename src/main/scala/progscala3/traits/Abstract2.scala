// src/main/scala/progscala3/traits/Abstract2.scala
package progscala3.traits

enum LoggingLevel:                                                   // <1>
  case Info, Warn, Error

trait Logging(name: String):
  import LoggingLevel.*
  final def info(message: String): Unit = log(Info, message)
  final def warn(message: String): Unit = log(Warn, message)
  final def error(message: String): Unit = log(Error, message)
  final def log(level: LoggingLevel, message: String): Unit =
    _log(s"${level.toString.toUpperCase} ($name): $message")         // <2>

  protected val _log: String => Unit                                 // <3>

trait ConsoleLogging extends Logging:
  protected val _log = println                                       // <4>

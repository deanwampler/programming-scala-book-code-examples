// src/main/scala/progscala3/traits/Abstract2.scala
package progscala3.traits

enum LoggingLevel:                                                   // <1>
	case INFO, WARN, ERROR

trait Logging(name: String):                                         // <2>
	import LoggingLevel._
	def info(message: String): Unit = log(INFO, message)
	def warn(message: String): Unit = log(WARN, message)
	def error(message: String): Unit = log(ERROR, message)
	def log(level: LoggingLevel, message: String): Unit =
		_log(s"$level ($name): $message")                                // <3>

	protected val _log: String => Unit                                 // <4>

trait ConsoleLogging extends Logging:
	protected val _log = println                                       // <5>

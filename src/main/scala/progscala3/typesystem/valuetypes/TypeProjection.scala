// src/main/scala/progscala3/typesystem/valuetypes/TypeProjection.scala
package progscala3.typesystem.valuetypes

trait Logger:                                                        // <1>
  def log(message: String): Unit

class ConsoleLogger extends Logger:                                  // <2>
  def log(message: String): Unit = println(s"log: $message")

trait Service:                                                       // <3>
  type Log <: Logger
  val logger: Log

class ConsoleService extends Service:                                // <4>
  type Log = ConsoleLogger
  val logger: ConsoleLogger = ConsoleLogger()

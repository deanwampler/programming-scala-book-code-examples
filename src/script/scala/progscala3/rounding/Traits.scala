// tag::service[]
// src/script/scala/progscala3/rounding/Traits.scala
import util.Random

open class Service(name: String):                               // <1>
  def work(i: Int): (Int, Int) = (i, Random.between(0, 1000))

val service1 = new Service("one")
(1 to 3) foreach (i => println(s"Result: ${service1.work(i)}"))
// end::service[]

// tag::logging[]
enum Level:                                                     // <1>
  case INFO, WARNING, ERROR
  def ==(other: Level): Boolean = this.ordinal == other.ordinal
  def >=(other: Level): Boolean = this.ordinal >= other.ordinal

trait Logging:
  import Level._

  def level: Level                                              // <2>
  def log(level: Level, message: String): Unit

  final def info(message: String): Unit =                       // <3>
    if level >= INFO then log(INFO, message)
  final def warning(message: String): Unit =
    if level >= WARNING then log(WARNING, message)
  final def error(message: String): Unit =
    if level >= ERROR then log(ERROR, message)

trait StdoutLogging extends Logging:                            // <4>
  def log(level: Level, message: String) =
    println(s"$level: $message")
// end::logging[]

// tag::example[]
case class LoggedService(name: String, level: Level)
    extends Service(name) with StdoutLogging:
  override def work(i: Int): (Int, Int) =
    info(s"Starting work: i = $i")
    val result = super.work(i)
    info(s"Ending work: result = $result")
    result

val service2 = LoggedService("two", Level.INFO)
(1 to 3) foreach (i => println(s"Result:  ${service2.work(i)}"))
// end::example[]

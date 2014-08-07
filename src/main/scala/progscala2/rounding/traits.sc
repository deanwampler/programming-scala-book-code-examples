// BEGIN SERVICE
// src/main/scala/progscala2/rounding/traits.sc

class ServiceImportante(name: String) {
  def work(i: Int): Int = {
    println(s"ServiceImportante: Doing important work! $i")
    i + 1
  }
}

val service1 = new ServiceImportante("uno")
(1 to 3) foreach (i => println(s"Result: ${service1.work(i)}"))
// END SERVICE

// BEGIN LOGGING
trait Logging {
  def info   (message: String): Unit
  def warning(message: String): Unit
  def error  (message: String): Unit
}

trait StdoutLogging extends Logging {
  def info   (message: String) = println(s"INFO:    $message")
  def warning(message: String) = println(s"WARNING: $message")
  def error  (message: String) = println(s"ERROR:   $message")
}
// END LOGGING

// BEGIN MIXED
val service2 = new ServiceImportante("dos") with StdoutLogging {
  override def work(i: Int): Int = {
    info(s"Starting work: i = $i")
    val result = super.work(i)
    info(s"Ending work: i = $i, result = $result")
    result
  }
}
(1 to 3) foreach (i => println(s"Result: ${service2.work(i)}"))

// END MIXED

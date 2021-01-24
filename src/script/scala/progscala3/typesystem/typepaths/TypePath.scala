// src/script/scala/progscala3/typesystem/typepaths/TypePath.scala

open class Service:                                                  // <1>
  class Logger:
    def log(message: String): Unit = println(s"log: $message")

  val logger: Logger = Logger()

val s1 = new Service
val s2 = new Service:
  override val logger: Logger = s1.logger                            // <2>

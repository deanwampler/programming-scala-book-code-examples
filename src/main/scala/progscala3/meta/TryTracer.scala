// src/main/scala/progscala3/meta/TryTracer.scala
package progscala3.meta

@main def TryTracer =
  tracer(1 + 1 == 2)  // This still just prints as "true"
  tracer("A string")
  tracer {
    val up = "Hello World!".toUpperCase
    println(up)
  }

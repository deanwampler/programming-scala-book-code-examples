// src/script/scala/progscala3/meta/compiletime/CodeOf.scala
// Example mentioned in the book, but not discussed there.
import scala.compiletime.codeOf
import java.time.Instant.now

// If you copy and paste these lines into the REPL, you'll see the
// generated strings contain terminal color-coding!
// Instead, use "scala .../CodeOf.scala", which will run the following
// main:
@main def TryCodeOf =
  println("The following lines print the expression strings we want:")
  println(codeOf(1 + 1 == 2))
  println(codeOf("A string"))
  println(codeOf {
    val up = "Hello World!".toUpperCase
    println(up)
  })

  // It would be great if the following trace method worked like the previous
  // println statements. It almost works but unfortunately, despite using a
  // by-name parameter and three inlines, the output "expr" string is computed
  // after evaluating the expression, at least for the simpler `1 + 1 == 2` case.
  // We have to use a macro for this to work. See the implementation of tracer
  // (Tracer.scala) and invariant (Invariant.scala) later in the Metaprogramming
  // chapter.
  inline def trace[T](inline expr: => T): T =
    inline val ce = codeOf(expr)
    printf(s"Trace -> ($now): $ce\n")
    val t = expr
    printf(s"Trace <- ($now): $ce\n")
    t

  println("\nCalls to 'trace', which don't work the way we want:")
  trace(1 + 1 == 2)
  trace("A string")
  trace {
    val up = "Hello World!".toUpperCase
    println(up)
  }

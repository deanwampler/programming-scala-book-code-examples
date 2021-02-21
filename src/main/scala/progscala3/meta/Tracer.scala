// src/main/scala/progscala3/meta/Tracer.scala
package progscala3.meta
import scala.quoted.*

/**
 * A simple Logger abstraction used by tracer.
 */
trait Logger:
  import java.time.Instant.now
  def trace(message: => Any): Unit = log(s"TRACE ($now): $message")
  // Other logging methods
  protected def log(s: String): Unit

object ConsoleLogger extends Logger:
  protected def log(s: String): Unit = println(s)

/**
 * Implement a "tracer" that logs entry and exit points for an expression.
 * Compare with our attempts to do this with just the codeOf method here:
 *   src/script/scala/progscala3/meta/compiletime/CodeOf.scala
 * In fact, this implementation doesn't fix the issue with "1 + 1 == 2" being
 * printed as "true". Therefore, using CodeOf is simpler, although it's possible
 * this implementation will have less byte code and runtime overhead.
 * A real implementation would make the Logger configurable.
 * See TryTracer.scala for an example program that uses tracer.
 */
object tracer:
  inline def apply[T](inline expr: => T): T =
    val s = traceStr(expr)
    ConsoleLogger.trace(s"->: $s")
    val t = expr
    ConsoleLogger.trace(s"<-: $s")
    t

  inline def traceStr[T](inline expr: => T): String = ${ traceStrImpl('expr) }

  def traceStrImpl[T](expr: Expr[T])(using Quotes): Expr[String] =
    val code: String = expr.show
    Expr(code)


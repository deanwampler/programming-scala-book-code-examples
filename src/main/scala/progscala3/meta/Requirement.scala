// src/main/scala/progscala3/meta/Requirement.scala
package progscala3.meta
import scala.quoted.*

/**
 * Requirement thrown if a requirement fails
 */
case class RequirementFailure protected (message: String)
  extends RuntimeException(message)

object RequirementFailure:
  /** Create an exception from a predicate string and message. */
  def apply(predicateString: String, message: String): RequirementFailure =
    new RequirementFailure(
      s"Requirement failure! predicate false: $predicateString. $message")

  /** Create an exception from a message. */
  def apply(message: String): RequirementFailure =
    new RequirementFailure(s"Requirement failure! $message")

/**
 * Implement a custom "requirement" checker. It works like Scala's built in
 * `assert` and `requirement` methods, but it will show the expression that failed
 * but not necessarily as written. For example, constant folding means that
 * expressions like `1+1==3` will just be `false`, while `i+j==3` will be
 * shown as something like `i.+(j) == 3`. Still useful, however.
 */
object requirement:

  /**
   * "Require" that a predicate is true. If not, throw an exception.
   * @param predicate by-name parameter that must evaluate to true.
   */
  inline def apply(
    inline predicate: => Boolean): Unit = apply(predicate, "")

  /**
   * "Require" that a predicate is true. If not, throw an exception
   * with the specified `message` for context.
   */
  inline def apply(
      inline predicate: => Boolean,
      inline message: String): Unit = ${ checkReq('predicate, 'message) }

  def checkReq(predicate: Expr[Boolean], message: Expr[String])(
    using Quotes) = '{
      if !($predicate) then
        throw RequirementFailure(${showExpr(predicate)}, ${showExpr(message)})
    }

  /** You already know a requirement has failed, so just throw the exception. .*/
  def fail(message: String): Nothing = throw RequirementFailure(message)

  private def showExpr[T](expr: Expr[T])(using Quotes): Expr[String] =
    val code: String = expr.show
    Expr(code)

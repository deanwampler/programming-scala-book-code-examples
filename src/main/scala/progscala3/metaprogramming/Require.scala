// src/main/scala/progscala3/metaprogramming/invariant.scala
package progscala3.metaprogramming
import scala.quoted._

/**
 * Requirement thrown if a requirement fails
 */
case class RequirementFailure(
  predicateString: String,
  note: String) extends RuntimeException(
    s"Requirement failure! predicate is false: $predicateString. $note")


/**
 * Implement a custom assertion checker. See usage in the test scripts.
 * Unfortunately, this simple implementation only returns "false" for
 * the expression, not the original source code. See this ScalaTest code
 * https://github.com/scalatest/scalatest/tree/3.1.x/scalactic.dotty/src/main/scala/org/scalactic
 * for a more complete (and complex) implementation.
 */
object require {                                                   // <3>

  /**
   * "Require" that a predicate is true. If not, throw an exception.
   * @param predicate by-name parameter that must evaluate to true.
   */
  inline def apply(
    inline predicate: => Boolean): Unit = apply(predicate, "")

  /**
   * "Require" that a predicate is true. If not, throw an exception
   * with the specified `note` for context.
   */
  inline def apply(
      inline predicate: => Boolean,
      inline note: String): Unit = ${ checkReq('predicate, 'note) }

  def checkReq(predicate: Expr[Boolean], note: Expr[String])(
      using QuoteContext) = '{
    if (!($predicate)) {
      throw new RequirementFailure(${showExpr(predicate)}, ${showExpr(note)})
    }
  }

  def showExpr[T](expr: Expr[T])(using QuoteContext): Expr[String] = {
    val code: String = expr.show
    Expr(code)
  }
}

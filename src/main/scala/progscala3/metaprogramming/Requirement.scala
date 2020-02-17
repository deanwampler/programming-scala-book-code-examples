// src/main/scala/progscala3/metaprogramming/Requirement.scala
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
 * Implement a custom "requirement" checker. It works like Scala's built in
 * `assert` and `requirement` methods, but it will show the expression that failed
 * but not necessarily as written. For example, constant folding means that
 * expressions like `1+1==3` will just be `false`, while `i+j==3` will be
 * shown as something like `i.+(j) == 3`. Still useful, however.
 */
object requirement {                                                   // <3>

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

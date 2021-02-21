// src/main/scala/progscala3/meta/Invariant.scala
package progscala3.meta
import scala.quoted.*                                                // <1>

object invariant:
  inline val ignore = false

  inline def apply[T](
      inline predicate: => Boolean, message: => String = "")(        // <2>
      inline block: => T): T =
    inline if !ignore then
      if !predicate then fail(predicate, message, block, "before")   // <3>
      val result = block
      if !predicate then fail(predicate, message, block, "after")
      result
    else
      block

  inline private def fail[T](
      inline predicate: => Boolean,
      inline message: => String,
      inline block: => T,
      inline beforeAfter: String): Unit =
    ${ failImpl('predicate, 'message, 'block, 'beforeAfter) }        // <4>

  case class InvariantFailure(msg: String) extends RuntimeException(msg)

  private def failImpl[T](
      predicate: Expr[Boolean], message: Expr[String],
      block: Expr[T], beforeAfter: Expr[String])(
      using Quotes): Expr[String] =
    '{ throw InvariantFailure(                                       // <5>
      s"""FAILURE! predicate "${${showExpr(predicate)}}" """
      + s"""failed ${$beforeAfter} evaluation of block:"""
      + s""" "${${showExpr(block)}}". Message = "${$message}". """)
    }

  private def showExpr[T](expr: Expr[T])(using Quotes): Expr[String] =
    val code: String = expr.show                                     // <6>
    Expr(code)

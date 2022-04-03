// src/main/scala/progscala3/meta/performance/InvariantMinInline.scala
// This code does not appear in the book.
package progscala3.meta.performance
import scala.quoted.*

object invariantMinInline:
  val ignore = false

  inline def apply[T](
      predicate: => Boolean, message: => String = "")(
      block: => T): T =
    if !ignore then
      if !predicate then fail(predicate, message, block, "before")
      val result = block
      if !predicate then fail(predicate, message, block, "after")
      result
    else
      block

  inline private def fail[T](
      predicate: => Boolean,
      message: => String,
      block: => T,
      beforeAfter: String): Unit =
    ${ failImpl('predicate, 'message, 'block, 'beforeAfter) }

  case class InvariantFailure(msg: String) extends RuntimeException(msg)

  private def failImpl[T](
      predicate: Expr[Boolean], message: Expr[String],
      block: Expr[T], beforeAfter: Expr[String])(
      using Quotes): Expr[String] =
    '{ throw InvariantFailure(
      s"""FAILURE! predicate "${${showExpr(predicate)}}" """
      + s"""failed ${$beforeAfter} evaluation of block:"""
      + s""" "${${showExpr(block)}}". Message = "${$message}". """)
    }

  private def showExpr[T](expr: Expr[T])(using Quotes): Expr[String] =
    val code: String = expr.show
    Expr(code)

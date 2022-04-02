package progscala3.erased

import scala.annotation.implicitNotFound
import scala.language.experimental.erasedDefinitions

sealed trait Emptiness
final class Empty extends Emptiness
final class NotEmpty extends Emptiness

@implicitNotFound("The seq must be empty")
class IsEmpty[-E <: Emptiness]
object IsEmpty:
  erased given IsEmpty[Empty] = new IsEmpty[Empty]

@implicitNotFound("The seq must be not empty")
class IsNotEmpty[-E <: Emptiness]
object IsNotEmpty:
  erased given IsNotEmpty[NotEmpty] = new IsNotEmpty[NotEmpty]

sealed trait ESeq[+H, T <: ESeq[H,?]]:
  type E <: Emptiness
  def hd: H
  def tl: T
  def head(using erased ne: IsNotEmpty[E]): H = hd
  def tail(using erased ne: IsNotEmpty[E]): T = tl
  def +:[H2 >: H](h2: H2): NotEmptySeq[H2, this.type]

case class NotEmptySeq[+H, T <: ESeq[H,?]] private (hd: H, tl: T) extends ESeq[H, T]:
  type E = NotEmpty
  def +:[H2 >: H](h2: H2): NotEmptySeq[H2, this.type] = 
    NotEmptySeq(h2, this)
  override def toString: String = s"$head +: ${tail.toString}"

object NotEmptySeq:
  def apply[H, T <: ESeq[H,?]](hd: H, tl: NotEmptySeq[H, T]): NotEmptySeq[H, NotEmptySeq[H, T]] = 
    new NotEmptySeq(hd, tl)
  def apply[H](hd: H): NotEmptySeq[H, EmptySeq.type] = new NotEmptySeq(hd, EmptySeq)

case object EmptySeq extends ESeq[Nothing, Nothing]:
  type E = Empty
  def hd: Nothing = ???
  def tl: Nothing = ???
  def +:[H](h: H): NotEmptySeq[H, this.type] = NotEmptySeq(h)
  override def toString: String = "EmptySeq"


// src/main/scala/progscala3/basicoop/tagging/Tags2.scala
package progscala3.basicoop.tagging
import scala.annotation.targetName

object Tagging2:
  /** Tagged[S, T] means that S is tagged with T */
  opaque type Tagged[S, T] = S

  /**
   * Define regular methods to covert between items and collections of items
   * to Tagged values.
   */
  object Tagged:
    def tag[S, T](s: S): Tagged[S, T] = s
    def untag[S, T](st: Tagged[S, T]): S = st

    def tags[F[_], S, T](fs: F[S]): F[Tagged[S, T]] = fs
    def untags[F[_], S, T](fst: F[Tagged[S, T]]): F[S] = fst

  /** A regular type alias for nice type syntax. Won't add runtime overhead. */
  @targetName("Tag") type @@[S, T] = Tagged[S, T]

  /**
   * Instead of extension methods, use implicit conversions to value classes,
   * which won't add any runtime overhead, but allow us to use methods like `tag`
   * the way we want. This implementation is closer to the original example in
   * SIP-35.
   */
  implicit class tagOps[S](s: S):
    def tag[T]: S @@ T = Tagged.tag(s)
  implicit class untagOps[S, T](st: S @@ T):
    def untag: S = Tagged.untag(st)
  implicit class tagsOps[F[_], S](fs: F[S]):
    def tags[T]: F[S @@ T] = Tagged.tags(fs)
  implicit class untagsOps[F[_], S, T](fst: F[S @@ T]):
    def untags: F[S] = Tagged.untags(fst)
end Tagging2

@main def TryTagging2(): Unit =
  import Tagging2.*
  trait Meter
  trait Foot

  val x: Double @@ Meter = (123.0).tag[Meter]
  val x1 = (123.0).tag[Meter]
  val y: Double @@ Foot = (123.0).tag[Foot]
  val y1 = (123.0).tag[Foot]
  assert(x == x1)
  assert(y == y1)
  // Danger!!
  assert(x == y)
  val xs = Array(1.0, 2.0, 3.0).tags[Meter]
  println(s"x, y, xs = $x, $y, ${xs.mkString(",")}")
  println(s"untagged: ${x.untag}, ${y.untag}, ${xs.untags.mkString(",")}")

  val o: Ordering[Double] = implicitly
  val om: Ordering[Double @@ Meter] = o.tags

  assert(om.compare(x, x) == 0)
  // Compilation Errors!
  // om.compare(x, y)
  // x == y
  assert(xs.min(om) == 1.0.tag[Meter])
  // Compilation Error!
  // xs.min(o)
end TryTagging2

// src/main/scala/progscala3/basicoop/tagging/Tags.scala
package progscala3.basicoop.tagging
import scala.annotation.targetName

/**
 * There are two variants of this example. This one uses extension methods, but
 * we observe a disadvantage for the user experience, discussed below.
 * See also Tags2.scala, which is closer to the original SIP-35 example, using
 * implicit conversions instead of extension methods, but providing a nicer
 * user API.
 */
object Tagging:
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

  /** Now define extension methods for users to use. */
  extension [S, T](s: S) def tag: S @@ T = Tagged.tag(s)
  extension [S, T](st: S @@ T) def untag: S = Tagged.untag(st)
  extension [F[_], S, T](fs: F[S]) def tags: F[S @@ T] = Tagged.tags(fs)
  extension [F[_], S, T](fst: F[S @@ T]) def untags: F[S] = Tagged.untags(fst)
end Tagging

@main def TryTagging(): Unit =
  import Tagging.*
  trait Meter
  trait Foot

  // Now we come to the problem with using extension methods, where we can't add
  // type parameters to the `tag` method. We have to put the types on the left-
  // hand side. Compare with Tags2.scala.
  val x: Double @@ Meter = (123.0).tag
  val y: Double @@ Foot = (123.0).tag
  // Danger!!
  assert(x == y)
  val xs: Array[Double @@ Meter] = Array(1.0, 2.0, 3.0).tags
  println(s"x, y, xs = $x, $y, ${xs.mkString(",")}")
  println(s"untagged: ${x.untag}, ${y.untag}, ${xs.untags.mkString(",")}")

  val o: Ordering[Double] = implicitly
  val om: Ordering[Double @@ Meter] = o.tags

  assert(om.compare(x, x) == 0)
  // Compilation Errors!
  // om.compare(x, y)
  val expected: Double @@ Meter = 1.0.tag
  assert(xs.min(om) == expected)
  // Compilation Error!
  // xs.min(o)
end TryTagging

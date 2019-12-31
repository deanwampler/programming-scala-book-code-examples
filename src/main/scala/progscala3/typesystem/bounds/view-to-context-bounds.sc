// src/main/scala/progscala3/typesystem/bounds/view-to-context-bounds.sc
import scala.language.implicitConversions

object Serialization {
  case class Rem[A](value: A) {
    def serialized: String = s"-- $value --"
  }
  type Writable[A] = A => Rem[A]                                     // <1>
  implicit val fromInt: Writable[Int]       = (i: Int)    => Rem(i)
  implicit val fromFloat: Writable[Float]   = (f: Float)  => Rem(f)
  implicit val fromString: Writable[String] = (s: String) => Rem(s)
}

import Serialization._

object RemoteConnection {
  def write[T : Writable](t: T): String =                            // <2>
    t.serialized  // Return a string "as the remote connection"
}

assert(RemoteConnection.write(100)      == "-- 100 --")              // <3>
assert(RemoteConnection.write(3.14f)    == "-- 3.14 --")
assert(RemoteConnection.write("hello!") == "-- hello! --")
// The following fails: "no implicit view from (Int, Int) => ...
// RemoteConnection.write((1, 2))

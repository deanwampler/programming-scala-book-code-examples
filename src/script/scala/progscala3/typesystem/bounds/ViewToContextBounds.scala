// src/script/scala/progscala3/typesystem/bounds/ViewToContextBounds.scala
// Not in the book; an example of using a context bound, given instances of
// Writable, where a view bound could be used, but is not deprecated.

object Serialization:
  case class Rem[A](value: A):
    def serialized: String = s"-- $value --"

  type Writable[A] = A => Rem[A]                                     // <1>
  given Writable[Int]    = (i: Int)    => Rem(i)
  given Writable[Float]  = (f: Float)  => Rem(f)
  given Writable[String] = (s: String) => Rem(s)

import Serialization._

object RemoteConnection:
  def write[T : Writable](t: T): String =                            // <2>
    val writable = implicitly
    writable(t).serialized  // Return a string "as the remote connection"

assert(RemoteConnection.write(100)      == "-- 100 --")              // <3>
assert(RemoteConnection.write(3.14f)    == "-- 3.14 --")
assert(RemoteConnection.write("hello!") == "-- hello! --")
// The following fails: "no implicit view from (Int, Int) => ...
RemoteConnection.write((1, 2))

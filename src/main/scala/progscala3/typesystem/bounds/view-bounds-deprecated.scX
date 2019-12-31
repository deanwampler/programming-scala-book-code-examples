// src/main/scala/progscala3/typesystem/bounds/view-bounds-deprecated.scX
import scala.language.implicitConversions

object Serialization {
  case class Writable(value: Any) {
    def serialized: String = s"-- $value --"                         // <1>
  }

  implicit def fromInt(i: Int) = Writable(i)                         // <2>
  implicit def fromFloat(f: Float) = Writable(f)
  implicit def fromString(s: String) = Writable(s)
}

import Serialization._

object RemoteConnection {                                            // <3>
  def write[T <% Writable](t: T): String =                           // <4>
    t.serialized  // Return a string "as the remote connection"
}

assert(RemoteConnection.write(100)      == "-- 100 --")
assert(RemoteConnection.write(3.14f)    == "-- 3.14 --")
assert(RemoteConnection.write("hello!") == "-- hello! --")
// The following fails: "no implicit view from (Int, Int) => ...
// RemoteConnection.write((1, 2))                                       <5>

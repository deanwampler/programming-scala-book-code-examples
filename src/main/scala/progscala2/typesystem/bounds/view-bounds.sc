// src/main/scala/progscala2/typesystem/bounds/view-bounds.sc
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
  def write[T <% Writable](t: T): Unit =                             // <4>
    println(t.serialized)  // Use stdout as the "remote connection"
}

RemoteConnection.write(100)       // Prints -- 100 --
RemoteConnection.write(3.14f)     // Prints -- 3.14 --
RemoteConnection.write("hello!")  // Prints -- hello! --
// RemoteConnection.write((1, 2))                                       <5>

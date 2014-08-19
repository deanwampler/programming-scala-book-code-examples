// src/main/scala/progscala2/typesystem/bounds/view-to-context-bounds.sc
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
  def write[T : Writable](t: T): Unit =                              // <2>
    println(t.serialized)  // Use stdout as the "remote connection"
}

RemoteConnection.write(100)       // Prints -- 100 --                   <3>
RemoteConnection.write(3.14f)     // Prints -- 3.14 --
RemoteConnection.write("hello!")  // Prints -- hello! --
// RemoteConnection.write((1, 2))

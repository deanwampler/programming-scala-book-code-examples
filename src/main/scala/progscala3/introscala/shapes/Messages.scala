// src/main/scala/progscala3/introscala/shapes/Messages.scala
package progscala3.introscala.shapes

sealed trait Message                                                 // <1>
case class Draw(shape: Shape) extends Message                        // <2>
case class Response(message: String) extends Message                 // <3>
case object Exit extends Message                                     // <4>

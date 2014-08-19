// src/main/scala/progscala2/introscala/shapes/ShapesDrawingActor.scala
package progscala2.introscala.shapes

object Messages {                                                    // <1>
  object Exit                                                        // <2>
  object Finished
  case class Response(message: String)                               // <3>
}

import akka.actor.Actor                                              // <4>

class ShapesDrawingActor extends Actor {                             // <5>
  import Messages._                                                  // <6>

  def receive = {                                                    // <7>
    case s: Shape =>
      s.draw(str => println(s"ShapesDrawingActor: $str"))
      sender ! Response(s"ShapesDrawingActor: $s drawn")
    case Exit =>
      println(s"ShapesDrawingActor: exiting...")
      sender ! Finished
    case unexpected =>  // default. Equivalent to "unexpected: Any"
      val response = Response(s"ERROR: Unknown message: $unexpected")
      println(s"ShapesDrawingActor: $response")
      sender ! response
  }
}

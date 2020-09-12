// src/main/scala/progscala3/introscala/shapesactor/ShapesDrawingActor.scala
package progscala3.introscala.shapesactor

import akka.actor.{Actor, actorRef2Scala}                            // <1>
import scala.language.implicitConversions

object Messages:                                                     // <2>
  object Exit                                                        // <3>
  object Finished
  case class Response(message: String)                               // <4>

class ShapesDrawingActor extends Actor:                              // <5>
  import Messages._                                                  // <6>

  def receive =                                                      // <7>
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
  end receive                // optional
end ShapesDrawingActor       // optional

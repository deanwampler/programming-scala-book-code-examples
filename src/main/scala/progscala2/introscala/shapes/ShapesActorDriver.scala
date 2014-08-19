// src/main/scala/progscala2/introscala/shapes/ShapesActorDriver.scala
package progscala2.introscala.shapes
import akka.actor.{Props, Actor, ActorRef, ActorSystem}
import com.typesafe.config.ConfigFactory

// Message used only in this file:
private object Start                                                 // <1>

object ShapesDrawingDriver {                                         // <2>
  def main(args: Array[String]) {                                    // <3>
    val system = ActorSystem("DrawingActorSystem", ConfigFactory.load())
    val drawer = system.actorOf(
      Props(new ShapesDrawingActor), "drawingActor")
    val driver = system.actorOf(
       Props(new ShapesDrawingDriver(drawer)), "drawingService")
    driver ! Start                                                   // <4>
  }
}

class ShapesDrawingDriver(drawerActor: ActorRef) extends Actor {     // <5>
  import Messages._

  def receive = {
    case Start =>                                                    // <6>
      drawerActor ! Circle(Point(0.0,0.0), 1.0)
      drawerActor ! Rectangle(Point(0.0,0.0), 2, 5)
      drawerActor ! 3.14159
      drawerActor ! Triangle(Point(0.0,0.0), Point(2.0,0.0), Point(1.0,2.0))
      drawerActor ! Exit
    case Finished =>                                                 // <7>
      println(s"ShapesDrawingDriver: cleaning up...")
      context.system.shutdown()
    case response: Response =>                                       // <8>
      println("ShapesDrawingDriver: Response = " + response)
    case unexpected =>                                               // <9>
      println("ShapesDrawingDriver: ERROR: Received an unexpected message = "
        + unexpected)
  }
}

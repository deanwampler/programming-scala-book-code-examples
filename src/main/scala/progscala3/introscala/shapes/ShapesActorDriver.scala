// src/main/scala/progscala3/introscala/shapes/ShapesActorDriver.scala
package progscala3.introscala.shapes
import akka.actor.{actorRef2Scala, Props, Actor, ActorRef, ActorSystem}
import com.typesafe.config.ConfigFactory
import scala.concurrent.ExecutionContext
import scala.language.implicitConversions

private object Start                                                 // <1>

object ShapesDrawingDriver:                                          // <2>
  def main(args: Array[String]): Unit =                              // <3>
    val system = ActorSystem("DrawingActorSystem", ConfigFactory.load())
    val drawer = system.actorOf(
      Props(new ShapesDrawingActor), "drawingActor")
    val driver = system.actorOf(
       Props(new ShapesDrawingDriver(drawer)), "drawingService")
    driver ! Start                                                   // <4>

class ShapesDrawingDriver(drawerActor: ActorRef) extends Actor:      // <5>
  import Messages._
  implicit val ec: ExecutionContext = ExecutionContext.global

  def receive =
    case Start =>                                                    // <6>
      drawerActor ! Circle(Point(0.0,0.0), 1.0)
      drawerActor ! Rectangle(Point(0.0,0.0), 2, 5)
      drawerActor ! 3.14159
      drawerActor ! Triangle(Point(0.0,0.0), Point(2.0,0.0), Point(1.0,2.0))
      drawerActor ! Exit
    case Finished =>                                                 // <7>
      println(s"ShapesDrawingDriver: cleaning up...")
      context.system.terminate().foreach(t => println(s"terminated: $t"))
    case response: Response =>                                       // <8>
      println("ShapesDrawingDriver: Response = " + response)
    case unexpected =>                                               // <9>
      println("ShapesDrawingDriver: ERROR: Received an unexpected message = "
        + unexpected)

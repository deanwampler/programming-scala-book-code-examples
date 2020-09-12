// src/main/scala/progscala3/introscala/shapesactor/ShapesActorDriver.scala
package progscala3.introscala.shapesactor
import akka.actor.{actorRef2Scala, Props, Actor, ActorRef, ActorSystem}
import com.typesafe.config.ConfigFactory
import scala.concurrent.ExecutionContext.Implicits.global            // <1>
import scala.language.implicitConversions

private case class Start(drawerActor: ActorRef)                      // <2>

@main def RunShapesDrawingDriver =                                   // <3>
  val system = ActorSystem("DrawingActorSystem", ConfigFactory.load())
  val drawer = system.actorOf(
    Props(new ShapesDrawingActor), "drawingActor")
  val driver = system.actorOf(
     Props(new ShapesDrawingDriver), "drawingService")
  driver ! Start(drawer)                                             // <4>

class ShapesDrawingDriver extends Actor:                             // <5>
  import Messages._

  def receive =
    case Start(drawerActor) =>                                       // <6>
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

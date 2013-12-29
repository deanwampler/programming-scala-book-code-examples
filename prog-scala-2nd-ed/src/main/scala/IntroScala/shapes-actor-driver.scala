// src/main/scala/IntroScala/shapes-actor-driver.scala

package intro.shapes {

  import akka.actor.{Props, Actor, ActorRef, ActorSystem}
  import com.typesafe.config.ConfigFactory

  // Message used only in this file:
  case class Start(drawer: ActorRef)

  object ShapesDrawingDriver {
    def main(args: Array[String]) {
      val system = ActorSystem("DrawingActorSystem", ConfigFactory.load())
      val driver = system.actorOf(Props[ShapesDrawingDriver], "drawingService")
      val drawer = system.actorOf(Props[ShapesDrawingActor], "drawingActor")
      driver ! Start(drawer)
    }
  }

  class ShapesDrawingDriver extends Actor {
    // Using null is HORRIBLY UNSAFE!! We'll see a better techniques later.
    var drawer: ActorRef = null

    import Messages._

    def receive = {
      case Start(drawerActor) =>
        drawer = drawerActor
        drawerActor ! Circle(Point(0.0,0.0), 1.0)
        drawerActor ! Rectangle(Point(0.0,0.0), 2, 5)
        drawerActor ! 3.14159
        drawerActor ! Triangle(Point(0.0,0.0), Point(2.0,0.0), Point(1.0,2.0))
        drawerActor ! Exit
      case Finished =>
        println(s"ShapesDrawingDriver: cleaning up...")
        context.system.shutdown()
      case response: Response =>
        println(s"ShapesDrawingDriver: Response = $response")
      case unexpected =>
        println(s"ShapesDrawingDriver: ERROR: Received an unexpected message = $unexpected")
    }
  }
}

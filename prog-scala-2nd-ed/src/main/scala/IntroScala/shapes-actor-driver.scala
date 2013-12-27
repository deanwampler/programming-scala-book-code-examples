// src/main/scala/IntroScala/shapes-actor-driver.scala

package intro.shapes {

  import akka.actor.{Props, Actor, ActorRef, ActorSystem, ActorLogging}
  import com.typesafe.config._

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

  class ShapesDrawingDriver extends Actor with ActorLogging {
    // Using null is HORRIBLY UNSAFE!! We'll see a better way
    // later when we discuss the Option type.
    var drawer: ActorRef = null

    def receive = {
      case Start(drawerActor) ⇒
        drawer = drawerActor
        drawerActor ! Circle(Point(0.0, 0.0), 1.0)
        drawerActor ! Rectangle(Point(0.0, 0.0), 2, 5)
        drawerActor ! 3.14159
        drawerActor ! "exit"
      case "good bye!" ⇒
        log.info("cleaning up...")
        context.system.shutdown()
      case message ⇒
        log.info(s"Response: $message")
    }
  }
}

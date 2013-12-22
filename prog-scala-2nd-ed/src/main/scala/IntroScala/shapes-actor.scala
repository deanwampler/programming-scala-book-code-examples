// code-examples/IntroducingScala/shapes-actor.scala

package intro.shapes {
  import akka.actor.{Actor, ActorLogging}

  class ShapesDrawingActor extends Actor with ActorLogging {
    def receive = {
      case s: Shape => 
        s.draw(s => log.info(s))
        sender ! s"$s drawn"
      case "exit" => 
        log.info("exiting...")
        sender ! "finished."
      case message =>  // default. Equivalent to "message: Any"
        val response = s"ERROR: Unknown message: $message"
        log.info(response)
        sender ! response
    }
  }
}

// src/main/scala/IntroScala/shapes-actor.scala

package intro.shapes {

  object Messages {
    object Exit
    object Finished
    case class Response(message: String)
  } 

  import akka.actor.Actor

  class ShapesDrawingActor extends Actor {
    import Messages._

    def receive = {
      case s: Shape => 
        s.draw(str => println(s"$this: $str"))
        sender ! Response(s"$this: $s drawn")
      case Exit => 
        println(s"$this: exiting...")
        sender ! Finished
      case unexpected =>  // default. Equivalent to "unexpected: Any"
        val response = Response(s"ERROR: Unknown message: $unexpected")
        println(s"$this: $response")
        sender ! response
    }
  }
}

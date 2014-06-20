// src/main/scala/AkkaActors/simple-actor.sc

import scala.actors.Actor

class Redford extends Actor {
  def act() {
    println("A lot of what acting is, is paying attention.")
  }
}

val robert = new Redford
robert.start

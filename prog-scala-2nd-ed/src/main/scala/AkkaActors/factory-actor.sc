// src/main/scala/AkkaActors/factory-actor.sc

import scala.actors.Actor
import scala.actors.Actor._

val paulNewman = actor {
  println("To be an actor, you have to be a child.")
}

// code-examples/Concurrency/factory-actor-script.scala

import scala.actors.Actor
import scala.actors.Actor._

val paulNewman = actor {
  println("To be an actor, you have to be a child.")
}
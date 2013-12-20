// code-examples/Concurrency/actor-mailbox-script.scala

import scala.actors.Actor
import scala.actors.Actor._

val countActor = actor {
  loop {
    react {
      case "how many?" => {
        println("I've got " + mailboxSize.toString + " messages in my mailbox.")
      }
    }
  }
}

countActor ! 1
countActor ! 2
countActor ! 3
countActor ! "how many?"
countActor ! "how many?"
countActor ! 4
countActor ! "how many?"

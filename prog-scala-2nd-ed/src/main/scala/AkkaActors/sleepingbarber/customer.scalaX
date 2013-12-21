// code-examples/Concurrency/sleepingbarber/customer.scala

package concurrency.sleepingbarber

import scala.actors.Actor
import scala.actors.Actor._

case object Haircut

class Customer(val id: Int) extends Actor {
  var shorn = false

  def act() = {
    loop {
      react {
        case Haircut => {
          shorn = true
          println("[c] customer " + id + " got a haircut")
        }
      }
    }
  }
}

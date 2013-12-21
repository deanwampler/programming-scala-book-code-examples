// code-examples/Concurrency/sleepingbarber/shop.scala

package concurrency.sleepingbarber

import scala.actors.Actor
import scala.actors.Actor._

class Shop extends Actor {
  val barber = new Barber()
  barber.start

  def act() {
    println("[s] the shop is open")

    loop {
      react {
        case customer: Customer => barber ! customer
      }
    }
  }
}

// src/main/scala/progscala2/appdesign/abstractions/twitter-shouting.sc

import progscala2.appdesign.abstractions._

trait TwitterClient {
  val userName: String
  def tweet(msg: String)
}

trait Shouting extends TwitterClient {
  abstract override def tweet(msg: String) = 
    super.tweet(msg.toUpperCase)
}

class FancyTwitterClient(val userName: String) {
  def tweet(msg: String) = println(userName + ": " + msg)
}

val client = new FancyTwitterClient("BuckTrends") with Shouting {
  override def tweet(msg: String) = super.tweet(msg)       // <1>
}

client.tweet("Is this thing on?")

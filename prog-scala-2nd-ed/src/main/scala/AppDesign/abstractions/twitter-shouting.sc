// code-examples/AppDesign/abstractions/twitter-shouting-script.scala

import appdesign.abstractions._

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

val client = new FancyTwitterClient("BuckTrends") with Shouting
client.tweet("Is this thing on?")

// src/main/scala/AppDesign/abstractions/twitter-clean.sc

import appdesign.abstractions._

trait TwitterClient {
    val userName: String
    def tweet(msg: String)
}

trait Cleanliness extends TwitterClient {
    abstract override def tweet(msg: String) = 
        if (msg.contains("dirty words") == false)
            super.tweet(msg)
}

class FancyTwitterClient(val userName: String) {
    def tweet(msg: String) = println(userName + ": " + msg)
}

val client = new FancyTwitterClient("BuckTrends") with Cleanliness
client.tweet("My second tweet.")
client.tweet("Can I use dirty words?")
client.tweet("My third tweet.")

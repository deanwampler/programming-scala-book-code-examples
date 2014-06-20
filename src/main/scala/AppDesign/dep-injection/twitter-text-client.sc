// src/main/scala/AppDesign/dep-injection/twitter-text-client.sc

import appdesign.abstractions.twitterclient._

val client = new TextClient(new TwitterUserProfile("BuckTrends"))
client.ui.sendTweet("My First Tweet. How's this thing work?")
client.ui.sendTweet("Is this thing on?")
client.ui.sendTweet("Heading to the bathroom...")
println("Chat history:")
client.localCache.history.foreach {t => println(t)}

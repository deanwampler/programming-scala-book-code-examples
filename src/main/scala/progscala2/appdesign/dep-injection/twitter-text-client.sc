// src/main/scala/progscala2/appdesign/dep-injection/twitter-text-client.sc

import progscala2.appdesign.abstractions.twitterclient._

val client = new TextClient(new TwitterUserProfile("BuckTrends"))
client.ui.sendTweet("My First Tweet. How's this thing work?")
client.ui.sendTweet("Is this thing on?")
client.ui.sendTweet("Heading to the bathroom...")
println("Chat history:")
client.localCache.history.foreach {t => println(t)}

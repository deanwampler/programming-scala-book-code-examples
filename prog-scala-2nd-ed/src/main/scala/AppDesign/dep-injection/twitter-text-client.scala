// src/main/scala/AppDesign/dep-injection/twitter-text-client.scala

package appdesign.twitterclient

class TextClient(userProfile: TwitterUserProfile) 
    extends TwitterClientComponent 
    with TwitterClientUIComponent
    with TwitterLocalCacheComponent
    with TwitterServiceComponent {
    
  // From TwitterClientComponent:
  
  val client = new TwitterClient(userProfile)

  // From TwitterClientUIComponent:
  
  val ui = new TwitterClientUI(client) {
    def showTweet(tweet: Tweet) = println(tweet)
  }
  
  // From TwitterLocalCacheComponent:
  
  val localCache = new TwitterLocalCache {
    private var tweets: List[Tweet] = Nil

    def saveTweet(tweet: Tweet) = tweets ::= tweet

    def history = tweets
  }
  
  // From TwitterServiceComponent
  
  val service = new TwitterService() {
    def sendTweet(tweet: Tweet) = {
      println("Sending tweet to Twitter HQ")
      true
    }
    def history = List[Tweet]()
  }
}

// code-examples/AppDesign/dep-injection/twitter-client.scala

package appdesign.twitterclient
import java.util.Date
import java.text.DateFormat

class TwitterUserProfile(val userName: String) {    
  override def toString = "@" + userName
}

case class Tweet(
  val tweeter: TwitterUserProfile,
  val message: String,
  val time: Date) {
  
  override def toString = "(" +
    DateFormat.getDateInstance(DateFormat.FULL).format(time) + ") " +
    tweeter + ": " + message
}

trait Tweeter {
  def tweet(message: String)
}

trait TwitterClientUIComponent {
  val ui: TwitterClientUI
  
  abstract class TwitterClientUI(val client: Tweeter) {
    def sendTweet(message: String) = client.tweet(message)
    def showTweet(tweet: Tweet): Unit
  }
}

trait TwitterLocalCacheComponent {
  val localCache: TwitterLocalCache

  trait TwitterLocalCache {
    def saveTweet(tweet: Tweet): Unit
    def history: List[Tweet]
  }
}

trait TwitterServiceComponent {
  val service: TwitterService

  trait TwitterService {
    def sendTweet(tweet: Tweet): Boolean
    def history: List[Tweet]
  }
}

trait TwitterClientComponent {
  self: TwitterClientUIComponent with
        TwitterLocalCacheComponent with 
        TwitterServiceComponent =>
  
  val client: TwitterClient
  
  class TwitterClient(val user: TwitterUserProfile) extends Tweeter {
    def tweet(msg: String) = {
      val twt = new Tweet(user, msg, new Date)
      if (service.sendTweet(twt)) {
        localCache.saveTweet(twt)
        ui.showTweet(twt)
      } 
    }
  }    
}

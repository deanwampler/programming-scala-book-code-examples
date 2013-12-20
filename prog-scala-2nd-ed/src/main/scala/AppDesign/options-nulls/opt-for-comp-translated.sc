// code-examples/AppDesign/options-nulls/opt-for-comp-translated-script.scala

case class User(userName: String, name: String, email: String, bio: String)

val newUserProfiles = List(
  Map("userName" -> "twitspam", "name" -> "Twit Spam"),
  Map("userName" -> "bucktrends", "name" -> "Buck Trends",
      "email" -> "thebuck@stops.he.re", "bio" -> "World's greatest bloviator"),
  Map("userName" -> "lonelygurl", "name" -> "Lonely Gurl", 
      "bio" -> "Obviously fake..."),
  Map("userName" -> "deanwampler", "name" -> "Dean Wampler", 
      "email" -> "dean@....com", "bio" -> "Scala passionista"),
  Map("userName" -> "al3x", "name" -> "Alex Payne", 
      "email" -> "al3x@....com", "bio" -> "Twitter API genius"))

// This is what the final version's for comprehension is translated into
// by the compiler.

var validUsers = newUserProfiles flatMap {
	case user => user.get("userName") flatMap {
		case userName => user.get("name") flatMap {
	  	case name => user.get("email") flatMap {
	  	  case email => user.get("bio") map {
	  	    case bio => User(name, userName, email, bio) 
	  	  }
	  	}
		}
	}
}

validUsers.foreach (user => println(user))

// code-examples/AppDesign/options-nulls/option-for-comp-script.scala

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

// Version #2 (final)

var validUsers = for {
  user     <- newUserProfiles 
  userName <- user get "userName" 
  name     <- user get "name" 
  email    <- user get "email"
  bio      <- user get "bio" }
    yield User(userName, name, email, bio)

validUsers.foreach (user => println(user))

// code-examples/TypeLessDoMore/user-profile-v28-script.scala
// Scala v2.8 only.

object OptionalUserProfileInfo {
  val UnknownLocation = ""
  val UnknownAge = -1
  val UnknownWebSite = ""
}

class OptionalUserProfileInfo(
  location: String = OptionalUserProfileInfo.UnknownLocation, 
  age: Int         = OptionalUserProfileInfo.UnknownAge,
  webSite: String  = OptionalUserProfileInfo.UnknownWebSite)
    
println( new OptionalUserProfileInfo )
println( new OptionalUserProfileInfo(age = 29) )
println( new OptionalUserProfileInfo(age = 29, location="Earth") )

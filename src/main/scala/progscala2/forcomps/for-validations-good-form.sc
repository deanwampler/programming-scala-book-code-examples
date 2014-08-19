// src/main/scala/progscala2/forcomps/for-validations-good-form.sc
import scalaz._, std.AllInstances._

/** Validate a user's name; nonempty and alphabetic characters, only. */
def validName(key: String, name: String):
    Validation[List[String], List[(String,Any)]] = {
  val n = name.trim  // remove whitespace
  if (n.length > 0 && n.matches("""^\p{Alpha}$""")) Success(List(key -> n))
  else Failure(List(s"Invalid $key <$n>"))
}

/** Validate that the string is an integer and greater than zero. */
def positive(key: String, n: String):
    Validation[List[String], List[(String,Any)]] = {
  try {
    val i = n.toInt
    if (i > 0) Success(List(key -> i))
    else Failure(List(s"Invalid $key $i"))
  } catch {
    case _: java.lang.NumberFormatException =>
      Failure(List(s"$n is not an integer"))
  }
}

def validateForm(firstName: String, lastName: String, age: String):
    Validation[List[String], List[(String,Any)]] = {
  validName("first-name", firstName) +++ validName("last-name", lastName) +++
    positive("age", age)
}

validateForm("Dean", "Wampler", "29")
// Returns: Success(List((first-name,Dean), (last-name,Wampler), (age,29)))
validateForm("", "Wampler", "29")
// Returns: Failure(List(Invalid first-name <>))
validateForm("D e a n", "Wampler", "29")
// Returns: Failure(List(Invalid first-name <D e a n>))
validateForm("D1e2a3n_", "Wampler", "29")
// Returns: Failure(List(Invalid first-name <D1e2a3n_>))
validateForm("Dean", "", "29")
// Returns: Failure(List(Invalid last-name <>))
validateForm("Dean", "Wampler", "0")
// Returns: Failure(List(Invalid age 0))
validateForm("Dean", "Wampler", "xx")
// Returns: Failure(List(xx is not an integer))
validateForm("", "Wampler", "0")
// Returns: Failure(List(Invalid first-name <>, Invalid age 0))
validateForm("Dean", "", "0")
// Returns: Failure(List(Invalid last-name <>, Invalid age 0))
validateForm("D e a n", "", "29")
// Returns: Failure(List(Invalid first-name <D e a n>, Invalid last-name <>))

// src/main/scala/progscala3/forcomps/for-validations-good-form1.scala

package progscala3.forcomps

object LoginValidatorSingle {                                // <1>

  type E[T] = Either[LoginValidation, T]  // shorter

  def nonEmpty(field: String, name: String): E[String] =
    Either.cond(
      field.length > 0,
      field,
      Empty(name))

  def notTooShort(field: String, name: String, n: Int): E[String] =
    Either.cond(
      field.length >= n,
      field,
      TooShort(name, n))

  /** For simplicity, just disallow whitespace. */
  def goodCharacters(field: String, name: String): E[String] = {
    val re = raw".*\s.*".r
    Either.cond(
      re.matches(field) == false,
      field,
      BadCharacters(name))
  }

  def apply(userName: String, password: String): E[ValidLoginForm] = // <2>
    for {
      _ <- nonEmpty(userName, "user name")
      _ <- notTooShort(userName, "user name", 5)
      _ <- goodCharacters(userName, "user name")
      _ <- nonEmpty(password, "password")
      _ <- notTooShort(password, "password", 5)
      _ <- goodCharacters(password, "password")
    } yield ValidLoginForm(userName, password)

  def main(args: Array[String]): Unit = {
    assert(LoginValidatorSingle("", "pwd") == 
      Left(Empty("user name")))
    assert(LoginValidatorSingle("", "") == 
      Left(Empty("user name")))   // <3>
    assert(LoginValidatorSingle("12", "") == 
      Left(TooShort("user name", 5)))
    assert(LoginValidatorSingle("12", "67") == 
      Left(TooShort("user name", 5))) 

    assert(LoginValidatorSingle("12345", "67") == 
      Left(TooShort("password", 5)), s"actual: ${LoginValidatorSingle("12345", "67")}")
    assert(LoginValidatorSingle("123 45", "67") == 
      Left(BadCharacters("user name")))
    assert(LoginValidatorSingle("12345", "678 90") == 
      Left(BadCharacters("password")))

    assert(LoginValidatorSingle("12345", "67890") == 
      Right(ValidLoginForm("12345", "67890")), s"actual: ${LoginValidatorSingle("12345", "67890")}")
  }
}

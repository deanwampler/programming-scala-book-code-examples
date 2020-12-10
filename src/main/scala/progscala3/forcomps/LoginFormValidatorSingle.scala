// src/main/scala/progscala3/forcomps/LoginFormValidatorSingle.scala

package progscala3.forcomps

object LoginFormValidatorSingle:

  type E[T] = Either[LoginValidation, T]                             // <1>

  def nonEmpty(field: String, name: String): E[String] =             // <2>
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
  def goodCharacters(field: String, name: String): E[String] =
    val re = raw".*\s.*".r
    Either.cond(
      re.matches(field) == false,
      field,
      BadCharacters(name))

  def apply(userName: String, password: String): E[ValidLoginForm] = // <3>
    for
      _ <- nonEmpty(userName, "user name")
      _ <- notTooShort(userName, "user name", 5)
      _ <- goodCharacters(userName, "user name")
      _ <- nonEmpty(password, "password")
      _ <- notTooShort(password, "password", 5)
      _ <- goodCharacters(password, "password")
    yield ValidLoginForm(userName, password)
end LoginFormValidatorSingle

/**
 * This method uses the matching clauses shown rather something like this:
 *   assert(LoginFormValidatoSingle(...) == Left(...))
 * This is necessary because we use -language:strictEquality, which causes
 * these == expressions to fail compilation!
 */
@main def TryLoginFormValidatorSingle =
  assert(LoginFormValidatorSingle("", "pwd") ==
    Left(Empty("user name")))

  assert(LoginFormValidatorSingle("", "") ==
    Left(Empty("user name")))

  assert(LoginFormValidatorSingle("12", "") ==
    Left(TooShort("user name", 5)))

  assert(LoginFormValidatorSingle("12", "67") ==
    Left(TooShort("user name", 5)))

  assert(LoginFormValidatorSingle("12345", "67") ==
    Left(TooShort("password", 5)))

  assert(LoginFormValidatorSingle("123 45", "67") ==
    Left(BadCharacters("user name")))

  assert(LoginFormValidatorSingle("12345", "678 90") ==
    Left(BadCharacters("password")))

  assert(LoginFormValidatorSingle("12345", "67890") ==
    Right(ValidLoginForm("12345", "67890")))
end TryLoginFormValidatorSingle

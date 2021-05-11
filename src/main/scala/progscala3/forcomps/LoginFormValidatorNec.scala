// src/main/scala/progscala3/forcomps/LoginFormValidatorNec.scala
package progscala3.forcomps

import cats.implicits.*
import cats.data.*
import cats.data.Validated.*

/**
 * Nec variant, where NEC stands for "non empty chain".
 * @see https://typelevel.org/cats/datatypes/validated.html
 */
object LoginFormValidatorNec:

  type V[T] = ValidatedNec[LoginValidation, T]                       // <1>

  def nonEmpty(field: String, name: String): V[String] =             // <2>
    if field.length > 0 then field.validNec
    else Empty(name).invalidNec

  def notTooShort(field: String, name: String, n: Int): V[String] =
    if field.length >= n then field.validNec
    else TooShort(name, n).invalidNec

  /** For simplicity, just disallow whitespace. */
  def goodCharacters(field: String, name: String): V[String] =
    val re = raw".*\s..*".r
    if re.matches(field) == false then field.validNec
    else BadCharacters(name).invalidNec

  def apply(                                                         // <3>
      userName: String, password: String): V[ValidLoginForm] =
    (nonEmpty(userName, "user name"),
    notTooShort(userName, "user name", 5),
    goodCharacters(userName, "user name"),
    nonEmpty(password, "password"),
    notTooShort(password, "password", 5),
    goodCharacters(password, "password")).mapN {
      case (s1, _, _, s2, _, _) => ValidLoginForm(s1, s2)
    }
end LoginFormValidatorNec

@main def TryLoginFormValidatorNec =
  import LoginFormValidatorNec.*
  assert(LoginFormValidatorNec("", "") ==
    Invalid(Chain(
      Empty("user name"), TooShort("user name", 5),
      Empty("password"), TooShort("password", 5))))

  assert(LoginFormValidatorNec("1234", "6789") ==
    Invalid(Chain(
      TooShort("user name", 5),
      TooShort("password", 5))))

  assert(LoginFormValidatorNec("12345", "") ==
    Invalid(Chain(
      Empty("password"), TooShort("password", 5))))

  assert(LoginFormValidatorNec("123 45", "678 90") ==
    Invalid(Chain(
      BadCharacters("user name"), BadCharacters("password"))))

  assert(LoginFormValidatorNec("12345", "67890") ==
    Valid(ValidLoginForm("12345", "67890")))
end TryLoginFormValidatorNec

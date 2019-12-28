// src/main/scala/progscala3/forcomps/for-validations-good-form2.scala
package progscala3.forcomps

import cats.implicits._
import cats.data._
import cats.data.Validated._

object LoginValidatorNec {                                   // <1>

  type V[T] = ValidatedNec[LoginValidation, T]  // shorter

  def nonEmpty(field: String, name: String): V[String] =
    if (field.length > 0) field.validNec 
    else Empty(name).invalidNec

  def notTooShort(field: String, name: String, n: Int): V[String] =
    if (field.length >= n) field.validNec 
    else TooShort(name, n).invalidNec

  /** For simplicity, just disallow whitespace. */
  def goodCharacters(field: String, name: String): V[String] = {
    val re = raw".*\s.*".r
    if (re.matches(field) == false) field.validNec 
    else BadCharacters(name).invalidNec
  }

  def apply(
      userName: String, password: String): V[ValidLoginForm] = // <2>
    (nonEmpty(userName, "user name"),
    notTooShort(userName, "user name", 5),
    goodCharacters(userName, "user name"),
    nonEmpty(password, "password"),
    notTooShort(password, "password", 5),
    goodCharacters(password, "password")).mapN { 
      case (s1, _, _, s2, _, _) => ValidLoginForm(s1, s2) 
    }

  def main(args: Array[String]): Unit = {
    assert(LoginValidatorNec("", "") == 
      Invalid(Chain(
        Empty("user name"), TooShort("user name", 5),
        Empty("password"), TooShort("password", 5))))    
    
    assert(LoginValidatorNec("1234", "6789") == 
      Invalid(Chain(
        TooShort("user name", 5),
        TooShort("password", 5))))      

    assert(LoginValidatorNec("12345", "") == 
      Invalid(Chain(
        Empty("password"), TooShort("password", 5)))) 
    
    assert(LoginValidatorNec("123 45", "678 90") == 
      Invalid(Chain(
        BadCharacters("user name"), BadCharacters("password"))))
    
    assert(LoginValidatorNec("12345", "67890") == 
      Valid(ValidLoginForm("12345", "67890")))
  }
}

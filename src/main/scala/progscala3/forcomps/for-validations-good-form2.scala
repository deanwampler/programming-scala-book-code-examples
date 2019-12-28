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

  protected case class Tri(s1: String, s2: String, s3: String)

  protected def validateField(
      field: String, name: String): V[String] = {              // <2>
    val vtri = 
      (nonEmpty(field, name),
      notTooShort(field, name, 5),
      goodCharacters(field, name)).mapN(Tri)
    
    vtri.map(tri => tri.s1)
  }

  def apply(
      userName: String, password: String): V[ValidLoginForm] = // <3>
    (validateField(userName, "user name"),
    validateField(password, "password")).mapN(ValidLoginForm)

  def main(args: Array[String]): Unit = {
    assert(LoginValidatorSingle("", "") == 
      Invalid(Chain(
        Empty("user name"),Empty("password"))), s"actual: ${LoginValidatorSingle("", "")}")    
        // Empty("user name"), TooShort("user name", 5),
        // Empty("password"), TooShort("password", 5))))    
    
    assert(LoginValidatorSingle("1234", "6789") == 
      Invalid(Chain(
        TooShort("user name", 5),
        TooShort("password", 5))))      

    assert(LoginValidatorSingle("12345", "") == 
      Invalid(Chain(
        Empty("password"), TooShort("password", 5)))) 
    
    assert(LoginValidatorSingle("123 45", "678 90") == 
      Invalid(Chain(
        BadCharacters("user name"), BadCharacters("password"))))
    
    assert(LoginValidatorSingle("12345", "67890") == 
      Valid(ValidLoginForm("me", "67890")))
  }
}

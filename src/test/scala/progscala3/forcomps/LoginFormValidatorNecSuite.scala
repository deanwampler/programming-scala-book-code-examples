// src/test/scala/progscala3/forcomps/LoginFormValidatorNecSuite.scala
package progscala3.forcomps

import cats.data.*
import cats.data.Validated.*
import munit.*

class LoginFormValidatorNecSuite extends FunSuite:
  test("empty and too short user and password reported") {
    assert(LoginFormValidatorNec("", "") ==
      Invalid(Chain(
        Empty("user name"), TooShort("user name", 5),
        Empty("password"), TooShort("password", 5))))
  }

  test("too short user and password reported") {
    assert(LoginFormValidatorNec("1234", "6789") ==
      Invalid(Chain(
        TooShort("user name", 5),
        TooShort("password", 5))))
  }

  test("valid user, empty and too short password reported") {
    assert(LoginFormValidatorNec("12345", "") ==
      Invalid(Chain(
        Empty("password"), TooShort("password", 5))))
  }

  test("invalid user and password reported") {
    assert(LoginFormValidatorNec("123 45", "678 90") ==
      Invalid(Chain(
        BadCharacters("user name"), BadCharacters("password"))))
  }

  test("valid user, invalid password reported") {
    assert(LoginFormValidatorNec("12345", "678 90") ==
      Invalid(Chain(BadCharacters("password"))))
  }

  test("valid user and short password") {
    assert(LoginFormValidatorNec("12345", "67890") ==
      Valid(ValidLoginForm("12345", "67890")))
  }

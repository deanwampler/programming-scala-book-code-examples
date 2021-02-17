// src/test/scala/progscala3/forcomps/LoginFormValidatorSingleSuite.scala
package progscala3.forcomps

import munit.*

class LoginFormValidatorSingleSuite extends FunSuite:
  test("empty user") {
    assert(LoginFormValidatorSingle("", "pwd") ==
      Left(Empty("user name")))
  }

  test("empty user and password, but only an empty user error is reported") {
    assert(LoginFormValidatorSingle("", "") ==
      Left(Empty("user name")))
  }

  test("short user and empty password, but only a short user error is reported") {
    assert(LoginFormValidatorSingle("12", "") ==
      Left(TooShort("user name", 5)))
  }

  test("short user and password, but only a short user error is reported") {
    assert(LoginFormValidatorSingle("12", "67") ==
      Left(TooShort("user name", 5)))
  }

  test("valid user and short password") {
    assert(LoginFormValidatorSingle("12345", "67") ==
      Left(TooShort("password", 5)))
  }

  test("invalid user and short password, but only an invalid user error is reported") {
    assert(LoginFormValidatorSingle("123 45", "67") ==
      Left(BadCharacters("user name")))
  }

  test("valid user and invalid password") {
    assert(LoginFormValidatorSingle("12345", "678 90") ==
      Left(BadCharacters("password")))
  }

  test("valid user and password") {
    assert(LoginFormValidatorSingle("12345", "67890") ==
      Right(ValidLoginForm("12345", "67890")))
  }

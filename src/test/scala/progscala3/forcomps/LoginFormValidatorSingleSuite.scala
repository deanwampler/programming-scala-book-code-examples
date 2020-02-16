// src/test/scala/progscala3/forcomps/LoginFormValidatorSingleSuite.scala
package progscala3.forcomps

import progscala3.metaprogramming.require

class LoginFormValidatorSingleSuite extends progscala3.FunSuite2 {

  test("empty user") {
    require(LoginFormValidatorSingle("", "pwd") ==
      Left(Empty("user name")))
  }

  test("empty user and password, but only an empty user error is reported") {
    require(LoginFormValidatorSingle("", "") ==
      Left(Empty("user name")))
  }

  test("short user and empty password, but only a short user error is reported") {
    require(LoginFormValidatorSingle("12", "") ==
      Left(TooShort("user name", 5)))
  }

  test("short user and password, but only a short user error is reported") {
    require(LoginFormValidatorSingle("12", "67") ==
      Left(TooShort("user name", 5)))
  }

  test("valid user and short password") {
    require(LoginFormValidatorSingle("12345", "67") ==
      Left(TooShort("password", 5)))
  }

  test("invalid user and short password, but only an invalid user error is reported") {
    require(LoginFormValidatorSingle("123 45", "67") ==
      Left(BadCharacters("user name")))
  }

  test("valid user and invalid password") {
    require(LoginFormValidatorSingle("12345", "678 90") ==
      Left(BadCharacters("password")))
  }

  test("valid user and password") {
    require(LoginFormValidatorSingle("12345", "67890") ==
      Right(ValidLoginForm("12345", "67890")))
  }
}

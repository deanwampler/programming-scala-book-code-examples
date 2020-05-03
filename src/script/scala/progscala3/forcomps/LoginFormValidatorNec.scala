// src/script/scala/progscala3/forcomps/LoginFormValidatorNec.scala
import cats.data._
import cats.data.Validated._

LoginFormValidatorNec("", "")
LoginFormValidatorNec("1234", "6789")
LoginFormValidatorNec("12345", "")
LoginFormValidatorNec("123 45", "678 90")
LoginFormValidatorNec("12345", "678 90")
LoginFormValidatorNec("12345", "67890")
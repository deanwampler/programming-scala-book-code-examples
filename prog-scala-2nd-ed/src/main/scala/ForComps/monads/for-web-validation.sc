// src/main/scala/ForComps/monads/for-web-validation.sc

import ForComps.Process
import scala.util.{ Try, Success, Failure }

object OldApi {
  case class Form(       // <1>
    creditCardNumber: Long, expirationMonth: Int, expirationYear: Int) {
    override def toString =
      f"$creditCardNumber%6d $expirationMonth%02d/$expirationYear%2d"
  }

  case class Session(sslOn: Boolean, loggedIn: Boolean) {   // <2>    
    override def toString =
      f"${sslOn.toString}%-5s ${loggedIn}%-5s"
  }
  case class OperationFailed(msg: String)            // <3>
    extends RuntimeException(msg) {
      override def toString = msg
    }

  def ensureValidSession(session: Session): Unit =   // <4>
    if (!session.sslOn) throw OperationFailed("Not using SSL!")
    else if (!session.loggedIn) throw OperationFailed("Not logged in!")

  def ensureValidCCNumber(form: Form): Unit =        // <5>
    if (form.creditCardNumber < 0) 
      throw OperationFailed("Invalid CC!")

  def ensureValidCCExpiration(form: Form): Unit =    // <6>
    if (form.expirationMonth < 1 || form.expirationMonth > 12 || 
        form.expirationYear < 2014) 
      throw OperationFailed("Invalid CC!")
}

object NewApi {
  import OldApi._

  type HttpResponseCode = Int                   // <7>

  def process(session: Session, form: Form): Try[HttpResponseCode] = 
    for {
      _ <- check(session, form)                 // <8>
      r <- processPayment(form)                 // <9>
    } yield r                                   // <10>

  def check(session: Session, form: Form): Try[Boolean] = 
    for {
      _ <- Try(ensureValidSession(session))     // <11>
      _ <- Try(ensureValidCCNumber(form))
      _ <- Try(ensureValidCCExpiration(form))
    } yield true                                // <12>

  def processPayment(form: Form): Try[HttpResponseCode] = {
    // ...
    Success(200)                                // <15>
  }
}

import OldApi._

val sessions = List(
  Session(false, true),
  Session(true,  false),
  Session(true,  true))

val forms = List(
  Form(    -1, 12, 2014),
  Form(123456,  0, 2014),
  Form(123456, 12, 2013),
  Form(123456, 12, 2014))

println("   Session:           Form:      Result:")
println("SSL?  logged in?    CC MM/YYYY")
for {
  session <- sessions
  form    <- forms
  result  = NewApi.process(session, form)
} println(f"${session}%-10s     $form%-12s   $result%s")

// Result: success1: scala.util.Try[NewApi.HttpResponseCode] = Success(200)

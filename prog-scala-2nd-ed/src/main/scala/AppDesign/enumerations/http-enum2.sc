// code-examples/AppDesign/enumerations/http-enum2-script.scala

object HttpMethod extends Enumeration {
  type Method = Value
  val Connect = Value("Connect")
  val Delete  = Value("Delete")
  val Get     = Value("Get")
  val Head    = Value("Head")
  val Options = Value("Options")
  val Post    = Value("Post")
  val Put     = Value("Put")
  val Trace   = Value("Trace")
}

import HttpMethod._

def handle (method: HttpMethod.Method) = method match {
  case Connect => println(method + ": " + method.id)
  case Delete  => println(method + ": " + method.id)
  case Get     => println(method + ": " + method.id)
  case Head    => println(method + ": " + method.id)
  case Options => println(method + ": " + method.id)
  case Post    => println(method + ": " + method.id)
  case Put     => println(method + ": " + method.id)
  case Trace   => println(method + ": " + method.id)
}

HttpMethod foreach { method => handle(method) }
println( HttpMethod )

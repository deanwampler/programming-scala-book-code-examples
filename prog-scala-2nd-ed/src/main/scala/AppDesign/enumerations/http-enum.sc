// code-examples/AppDesign/enumerations/http-enum-script.scala

object HttpMethod extends Enumeration {
  type Method = Value
  val Connect, Delete, Get, Head, Options, Post, Put, Trace = Value
}

import HttpMethod._

def handle (method: HttpMethod.Method) = method match {
  case Connect => println("Connect: " + method.id)
  case Delete  => println("Delete: "  + method.id)
  case Get     => println("Get: "     + method.id)
  case Head    => println("Head: "    + method.id)
  case Options => println("Options: " + method.id)
  case Post    => println("Post: "    + method.id)
  case Put     => println("Put: "     + method.id)
  case Trace   => println("Trace: "   + method.id)
}

HttpMethod foreach { method => handle(method) }
println( HttpMethod )

// code-examples/AppDesign/enumerations/http-enum3-script.scala

object HttpMethod extends Enumeration(
    "Connect", "Delete", "Get", "Head", "Options", "Post", "Put", "Trace") {
  type Method = Value
  val Connect, Delete, Get, Head, Options, Post, Put, Trace = Value
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

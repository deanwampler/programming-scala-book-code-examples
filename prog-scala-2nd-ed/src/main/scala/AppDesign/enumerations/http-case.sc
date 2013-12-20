// code-examples/AppDesign/enumerations/http-case-script.scala

sealed abstract class HttpMethod(val id: Int) {
  def name = getClass getSimpleName
  override def toString = name
}

case object Connect extends HttpMethod(0)
case object Delete  extends HttpMethod(1)
case object Get     extends HttpMethod(2)
case object Head    extends HttpMethod(3)
case object Options extends HttpMethod(4)
case object Post    extends HttpMethod(5)
case object Put     extends HttpMethod(6)
case object Trace   extends HttpMethod(7)

def handle (method: HttpMethod) = method match {
  case Connect => println(method + ": " + method.id)
  case Delete  => println(method + ": " + method.id)
  case Get     => println(method + ": " + method.id)
  case Head    => println(method + ": " + method.id)
  case Options => println(method + ": " + method.id)
  case Post    => println(method + ": " + method.id)
  case Put     => println(method + ": " + method.id)
  case Trace   => println(method + ": " + method.id)
}

List(Connect, Delete, Get, Head, Options, Post, Put, Trace) foreach { 
  method => handle(method) 
}

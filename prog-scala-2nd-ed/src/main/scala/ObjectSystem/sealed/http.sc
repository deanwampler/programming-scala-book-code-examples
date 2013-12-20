// code-examples/ObjectSystem/sealed/http-script.scala

sealed abstract class HttpMethod()
case class Connect(body: String) extends HttpMethod
case class Delete (body: String) extends HttpMethod
case class Get    (body: String) extends HttpMethod
case class Head   (body: String) extends HttpMethod
case class Options(body: String) extends HttpMethod
case class Post   (body: String) extends HttpMethod
case class Put    (body: String) extends HttpMethod
case class Trace  (body: String) extends HttpMethod

def handle (method: HttpMethod) = method match {
  case Connect (body) => println("connect: " + body)
  case Delete  (body) => println("delete: "  + body)
  case Get     (body) => println("get: "     + body)
  case Head    (body) => println("head: "    + body)
  case Options (body) => println("options: " + body)
  case Post    (body) => println("post: "    + body)
  case Put     (body) => println("put: "     + body)
  case Trace   (body) => println("trace: "   + body)
}

val methods = List(
  Connect("connect body..."),
  Delete ("delete body..."),
  Get    ("get body..."),
  Head   ("head body..."),
  Options("options body..."),
  Post   ("post body..."),
  Put    ("put body..."),
  Trace  ("trace body..."))

methods.foreach { method => handle(method) }

// src/main/scala/progscala3/patternmatching/http.sc

sealed abstract class HttpMethod() {                                 // <1>
    def body: String                                                 // <2>
    def bodyLength = body.length
}

case class Connect(body: String) extends HttpMethod                  // <3>
case class Delete (body: String) extends HttpMethod
case class Get    (body: String) extends HttpMethod
case class Head   (body: String) extends HttpMethod
case class Options(body: String) extends HttpMethod
case class Post   (body: String) extends HttpMethod
case class Put    (body: String) extends HttpMethod
case class Trace  (body: String) extends HttpMethod

def handle (method: HttpMethod) = method match {                     // <4>
  case Connect (body) => s"connect: (length: ${method.bodyLength}) $body"
  case Delete  (body) => s"delete:  (length: ${method.bodyLength}) $body"
  case Get     (body) => s"get:     (length: ${method.bodyLength}) $body"
  case Head    (body) => s"head:    (length: ${method.bodyLength}) $body"
  case Options (body) => s"options: (length: ${method.bodyLength}) $body"
  case Post    (body) => s"post:    (length: ${method.bodyLength}) $body"
  case Put     (body) => s"put:     (length: ${method.bodyLength}) $body"
  case Trace   (body) => s"trace:   (length: ${method.bodyLength}) $body"
}

assert(handle(Connect("connect body...")) == 
  "connect: (length: 15) connect body...")
assert(handle(Delete ("delete body..."))  == 
  "delete:  (length: 14) delete body...")
assert(handle(Get    ("get body..."))     == 
  "get:     (length: 11) get body...")
assert(handle(Head   ("head body..."))    == 
  "head:    (length: 12) head body...")
assert(handle(Options("options body...")) == 
  "options: (length: 15) options body...")
assert(handle(Post   ("post body..."))    == 
  "post:    (length: 12) post body...")
assert(handle(Put    ("put body..."))     == 
  "put:     (length: 11) put body...")
assert(handle(Trace  ("trace body..."))   == 
  "trace:   (length: 13) trace body...")

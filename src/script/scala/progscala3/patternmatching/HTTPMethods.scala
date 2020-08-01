// src/script/scala/progscala3/patternmatching/HTTPMethods.scala

enum HttpMethod:                                                // <1>
  def body: String                                              // <2>

  case Connect(body: String)                                    // <3>
  case Delete (body: String)
  case Get    (body: String)
  case Head   (body: String)
  case Options(body: String)
  case Post   (body: String)
  case Put    (body: String)
  case Trace  (body: String)

import HttpMethod._
def handle (method: HttpMethod) = method match                  // <4>
  case Connect (body) => s"connect: (length: ${method.body.length}) $body"
  case Delete  (body) => s"delete:  (length: ${method.body.length}) $body"
  case Get     (body) => s"get:     (length: ${method.body.length}) $body"
  case Head    (body) => s"head:    (length: ${method.body.length}) $body"
  case Options (body) => s"options: (length: ${method.body.length}) $body"
  case Post    (body) => s"post:    (length: ${method.body.length}) $body"
  case Put     (body) => s"put:     (length: ${method.body.length}) $body"
  case Trace   (body) => s"trace:   (length: ${method.body.length}) $body"

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

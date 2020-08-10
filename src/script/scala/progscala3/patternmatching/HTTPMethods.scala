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
  case Connect (body) => s"Connect: length = ${body.length}, body = $body"
  case Delete  (body) => s"Delete:  length = ${body.length}, body = $body"
  case Get     (body) => s"Get:     length = ${body.length}, body = $body"
  case Head    (body) => s"Head:    length = ${body.length}, body = $body"
  case Options (body) => s"Options: length = ${body.length}, body = $body"
  case Post    (body) => s"Post:    length = ${body.length}, body = $body"
  case Put     (body) => s"Put:     length = ${body.length}, body = $body"
  case Trace   (body) => s"Trace:   length = ${body.length}, body = $body"

assert(handle(Connect("CONNECT")) == "Connect: length = 7, body = CONNECT")
assert(handle(Delete ("DELETE"))  == "Delete:  length = 6, body = DELETE")
assert(handle(Get    ("GET"))     == "Get:     length = 3, body = GET")
assert(handle(Head   ("HEAD"))    == "Head:    length = 4, body = HEAD")
assert(handle(Options("OPTIONS")) == "Options: length = 7, body = OPTIONS")
assert(handle(Post   ("POST"))    == "Post:    length = 4, body = POST")
assert(handle(Put    ("PUT"))     == "Put:     length = 3, body = PUT")
assert(handle(Trace  ("TRACE"))   == "Trace:   length = 5, body = TRACE")

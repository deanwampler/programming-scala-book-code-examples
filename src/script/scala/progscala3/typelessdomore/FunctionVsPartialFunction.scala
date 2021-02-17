// src/script/scala/progscala3/typelessdomore/FunctionVsPartialFunction.scala
import progscala3.introscala.shapes.*

val func: Message => String = message => message match
  case Exit => "Got Exit"
  case Draw(shape) => s"Got Draw($shape)"
  case Response(str) => s"Got Response($str)"

val pfunc: PartialFunction[Message, String] =
  case Exit => "Got Exit"
  case Draw(shape) => s"Got Draw($shape)"
  case Response(str) => s"Got Response($str)"

func(Draw(Circle(Point(0.0,0.0), 1.0)))
pfunc(Draw(Circle(Point(0.0,0.0), 1.0)))
func(Response(s"Say hello to pi: 3.14159"))
pfunc(Response(s"Say hello to pi: 3.14159"))

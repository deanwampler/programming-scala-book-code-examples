// src/main/scala/Implicits/type-classes-subtyping.sc

trait Stringizer[+T] {
  def stringize: String
}

implicit class AnyStringizer(a: Any) extends Stringizer[Any] {
  def stringize: String = a match {
    case s: String => s
    case i: Int => (i*10).toString
    case f: Float => (f*10.1).toString
    case other => 
      throw new UnsupportedOperationException(s"Can't stringize $other")
  }
}

val list = List(1, 2.2F, "three", 'symbol)

list foreach ((x:Any) => println(s"$x: ${x.stringize}"))

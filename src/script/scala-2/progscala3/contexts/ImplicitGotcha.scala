// src/script/scala-2/progscala3/contexts/ImplicitGotcha.scala

trait Context                                              // <1>
implicit object SomeContext extends Context

case class Worker[T](seed: T)(implicit c: Context) {       // <2>
  def apply(value: T): String = s"$seed, $value"
}

val i = Worker(-5)(2)                                      // <3>

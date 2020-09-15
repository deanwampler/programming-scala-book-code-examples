// src/script/scala/progscala3/contexts/UsingClausesLists.scala

case class U1[T](t: T)
case class U2[T](t: T)

def f1[T1,T2](name: String)(using u1: U1[T1], u2: U2[T2]): String =       // <1>
  s"f1: $name: $u1, $u2"
def f2[T1,T2](name: String)(using u1: U1[T1])(using u2: U2[T2]): String = // <2>
  s"f2: $name: $u1, $u2"
def f3[T1,T2](name: String)(using u1: U1[T1])(u2: U2[T2]): String =       // <3>
  s"f3: $name: $u1, $u2"

given u1i as U1[Int](0)
given u2s as U2[String]("one")

f1("f1a")
f1("f1b")(using u1i, u2s)
f2("f2a")
f2("f2b")(using u1i)(using u2s)
val x3a = f3("f3a")
val x3b = f3("f3b")(using u1i)
f3("f3c")(using u1i)(u2s)

val u2a = U2[Any](1.1)
x3a(u2a)
x3b(u2a)


// src/script/scala/progscala3/typesystem/selftype/ThisAlias.scala

class C1:
  c1this =>                                                          // <1>

  def talk(message: String): String = "C1.talk: " + message
  class C2:
    class C3:
      def talk(message: String) = c1this.talk("C3.talk: " + message) // <2>
    val c3 = C3()
  val c2 = C2()

val c1 = C1()
assert(c1.talk("Hello") == "C1.talk: Hello")                         // <3>
assert(c1.c2.c3.talk("World") == "C1.talk: C3.talk: World")          // <4>

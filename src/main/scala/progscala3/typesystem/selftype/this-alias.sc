// src/main/scala/progscala3/typesystem/selftype/this-alias.sc

class C1 { self =>                                                   // <1>
  def talk(message: String): String = "C1.talk: " + message
  class C2 {
    class C3 {
      def talk(message: String) = self.talk("C3.talk: " + message)   // <2>
    }
    val c3 = new C3
  }
  val c2 = new C2
}
val c1 = new C1
assert(c1.talk("Hello") == "C1.talk: Hello")                         // <3>
assert(c1.c2.c3.talk("World") == "C1.talk: C3.talk: World")          // <4>

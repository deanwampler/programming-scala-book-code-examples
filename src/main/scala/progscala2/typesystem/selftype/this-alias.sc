// src/main/scala/progscala2/typesystem/selftype/this-alias.sc

class C1 { self =>                                                   // <1>
  def talk(message: String) = println("C1.talk: " + message)
  class C2 {
    class C3 {
      def talk(message: String) = self.talk("C3.talk: " + message)   // <2>
    }
    val c3 = new C3
  }
  val c2 = new C2
}
val c1 = new C1
c1.talk("Hello")                                                     // <3>
c1.c2.c3.talk("World")                                               // <4>

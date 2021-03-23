// src/main/scala/progscala3/introscala/UpperMain1.scala
package progscala3.introscala                                   // <1>

object UpperMain1:
  def main(params: Array[String]): Unit =                       // <2>
    print("UpperMain1.main: ")
    params.map(s => s.toUpperCase).foreach(s => printf("%s ",s))
    println("")

def main(params: Array[String]): Unit =                         // <3>
  print("main: ")
  params.map(s => s.toUpperCase).foreach(s => printf("%s ",s))
  println("")

@main def Hello(params: String*): Unit =                        // <4>
  print("Hello: ")
  params.map(s => s.toUpperCase).foreach(s => printf("%s ",s))
  println("")

// src/main/scala/progscala3/introscala/UpperMain1.scala
package progscala3.introscala                                   // <1>

object UpperMain1:
  def main(params: Array[String]): Unit =                         // <2>
    params.map(s => s.toUpperCase()).foreach(s => printf("%s ",s))
    println("")
  end main                                                      // <3>

  @main def hello(params: String*) = main(params.toArray)       // <4>
end UpperMain1                                                  // <5>

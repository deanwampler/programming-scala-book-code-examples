// src/main/scala/progscala3/introscala/UpperMain1.scala
package progscala3.introscala                                   // <1>

object UpperMain1:
  def main(args: Array[String]): Unit =                         // <2>
    args.map(s => s.toUpperCase()).foreach(s => printf("%s ",s))
    println("")
  end main                                                      // <3>

  @main def hello = main(Array("Hello", "World!"))              // <4>
end UpperMain1                                                  // <5>
// src/main/scala/progscala3/introscala/UpperMain1.scala
package progscala3.introscala

object UpperMain1:
  def main(args: Array[String]): Unit =
    args.map(s => s.toUpperCase()).foreach(s => printf("%s ",s))
    println("")

// src/main/scala/progscala3/introscala/UpperMain2.scala
package progscala3.introscala

object UpperMain2 {
  def main(args: Array[String]): Unit = {
    val output = args.map(_.toUpperCase()).mkString(" ")
    println(output)
  }
}

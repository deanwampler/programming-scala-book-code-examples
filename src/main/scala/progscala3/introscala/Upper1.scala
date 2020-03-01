// src/main/scala/progscala3/introscala/upper1.scala
package progscala3.introscala

object Upper1 {
  def main(args: Array[String]): Unit = {
    args.map(s => s.toUpperCase()).foreach(s => printf("%s ",s)) // <1>
    println("")
  }
}

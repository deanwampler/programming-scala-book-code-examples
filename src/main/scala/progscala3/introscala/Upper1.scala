// src/main/scala/progscala3/introscala/upper1.scala
package progscala3.introscala

object Upper1 {
  def main(args: Array[String]): Unit = {
    args.map(_.toUpperCase()).foreach(printf("%s ",_))
    println("")
  }
}

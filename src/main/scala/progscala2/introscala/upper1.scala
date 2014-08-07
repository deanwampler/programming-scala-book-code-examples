// src/main/scala/progscala2/introscala/upper1.scala

package progscala2.introscala

object Upper {
  def main(args: Array[String]) = {
    args.map(_.toUpperCase()).foreach(printf("%s ",_))
    println("")
  }
}

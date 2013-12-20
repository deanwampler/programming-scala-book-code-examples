// code-examples/TypeLessDoMore/string-util-client.scala

package typeless

object StringUtilClient {
  def main(args: Array[String]) = {
    args foreach { s => 
			StringUtilV4.toCollection(s).foreach { x => println(x) } 
		}
  }
}

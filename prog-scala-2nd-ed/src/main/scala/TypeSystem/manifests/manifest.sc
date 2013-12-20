// code-examples/TypeSystem/manifests/manifest-script.scala

import scala.reflect.Manifest

object WhichList {
  def apply[B](value: List[B])(implicit m: Manifest[B]) = m.toString match {
    case "int"              => println( "List[Int]" )
    case "double"           => println( "List[Double]" )
    case "java.lang.String" => println( "List[String]" )
    case _                  => println( "List[???]" )
  }
}

WhichList(List(1, 2, 3))
WhichList(List(1.1, 2.2, 3.3))
WhichList(List("one", "two", "three"))

List(List(1, 2, 3), List(1.1, 2.2, 3.3), List("one", "two", "three")) foreach {
  WhichList(_)
}

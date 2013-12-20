// code-examples/AdvOOP/objects/list-unapply-example-script.scala

val list = List(1, 2.2, "three", 'four)
list match {
  case List(x, y, _*) => println("x = "+x+", y = "+y)
  case _ => throw new Exception("No match! "+list)
}

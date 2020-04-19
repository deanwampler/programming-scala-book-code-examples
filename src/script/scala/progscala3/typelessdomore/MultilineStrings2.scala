// src/script/scala/progscala3/typelessdomore/MultilineStrings2.scala

def goodbye(name: String) =
  s"""xxxGoodbye, ${name}yyy
  xxxCome again!yyy""".stripPrefix("xxx").stripSuffix("yyy")

val bye = goodbye("Programming Scala")

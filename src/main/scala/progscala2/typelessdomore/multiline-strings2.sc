// src/main/scala/progscala2/typelessdomore/multiline-strings2.sc

def goodbye(name: String) = 
  s"""xxxGoodbye, ${name}yyy
  xxxCome again!yyy""".stripPrefix("xxx").stripSuffix("yyy")

goodbye("Programming Scala")

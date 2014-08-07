// src/main/scala/progscala2/typelessdomore/method-broad-inference-return.sc

def makeList(strings: String*) = {
  if (strings.length == 0)
    Nil  // #1
  else
    strings.toList
}

val list: List[String] = makeList()  // ERROR

// src/main/scala/progscala3/typelessdomore/MethodBroadInferenceReturn.sc
// ERROR: Won't compile. Method actually returns List[Any]; too "broad".

def makeList(strings: String*) = {
  if (strings.length == 0)
    List(0)  // #1
  else
    strings.toList
}

val list: List[String] = makeList()  // COMPILATION ERROR

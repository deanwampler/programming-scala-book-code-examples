// src/script/scala/progscala3/typesystem/bounds/list/ListAb.scala
import progscala3.typesystem.bounds.list.*

//val languages = AbbrevList("Scala", "Java", "Ruby", "C#", "C++", "Python")
val languages: AbbrevList[String] =
  "Scala" :: "Java" :: "Ruby" :: "C#" :: "C++" :: "Python" :: AbbrevNil
val list: AbbrevList[Any] = 3.14 :: languages

assert(languages.toString ==
  "::(Scala,::(Java,::(Ruby,::(C#,::(C++,::(Python,AbbrevNil))))))")
assert(list.toString ==
  "::(3.14,::(Scala,::(Java,::(Ruby,::(C#,::(C++,::(Python,AbbrevNil)))))))")

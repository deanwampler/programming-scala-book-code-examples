// src/script/scala/progscala3/typelessdomore/Null.scala

import java.util.HashMap as JHashMap                       // <1>

val jhm = JHashMap[String,String]()
jhm.put("one", "1")

val one1: String = jhm.get("one")                          // <2>
val one2: String | Null = jhm.get("one")                   // <3>

val two1: String = jhm.get("two")                          // <4>
val two2: String | Null = jhm.get("two")

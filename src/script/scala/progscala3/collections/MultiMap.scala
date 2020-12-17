// src/script/scala/progscala3/collections/MultiMap.scala
import collection.mutable.{HashMap, MultiMap, Set}                // <1>

val mm = new HashMap[Int, Set[String]] with MultiMap[Int, String] // <2>

mm.addBinding(1, "a")                                             // <3>
mm.addBinding(2, "b")
mm.addBinding(1, "c")                                             // <4>

mm.entryExists(1, _ == "a") == true                               // <5>
mm.entryExists(1, _ == "b") == false
mm.entryExists(2, _ == "b") == true

mm.removeBinding(1, "a")                                          // <6>
mm.entryExists(1, _ == "a") == false
mm.entryExists(1, _ == "c") == true

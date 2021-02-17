// src/script/scala/progscala3/javainterop/CollectionConverters.scala

import scala.jdk.CollectionConverters.*
import scala.collection.mutable.Set

val s = Set("one")
val js = s.asJava     // js: java.util.Set[String] = [one]

js.add("two")

assert(s == Set("two", "one"))

assert(js.asScala == Set("two", "one"))

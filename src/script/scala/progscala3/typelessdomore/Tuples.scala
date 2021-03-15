// src/script/scala/progscala3/typelessdomore/Tuples.scala

val t = ("Hello", 1, 2.3)                                            // <1>
println("Print the whole tuple: " + t)
println("Print the first item:  " + t._1)                            // <2>
println("Print the second item: " + t._2)
println("Print the third item:  " + t._3)

val (t1, t2, t3) = ("World", '!', 0x22)                              // <3>
println(t1 + ", " + t2 + ", " + t3)

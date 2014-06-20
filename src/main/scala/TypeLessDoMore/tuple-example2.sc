// src/main/scala/TypeLessDoMore/tuple-example2.sc

def tupleator[A,B,C](x1: A, x2: B, x3: C) = (x1, x2, x3)

val t = tupleator("Hello", 1, 2.3)
println( "Print the whole tuple: " + t )
println( "Print the first item:  " + t._1 )
println( "Print the second item: " + t._2 )
println( "Print the third item:  " + t._3 )

val (t1, t2, t3) = tupleator("World", '!', 0x22)
println( t1 + " " + t2 + " " + t3 )   

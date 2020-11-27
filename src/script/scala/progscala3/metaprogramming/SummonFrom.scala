// src/script/scala/progscala3/metaprogramming/SummonFrom.scala

import compiletime.{summonFrom, constValue}

inline def trySummonFrom() =                                    // <1>
	summonFrom {
		// case i: Int    => s"found int $i"
		// case s: String => s"found string $s"
		case i as given Int    => s"found int $i"
		case s as given String => s"found string $s"
	}

def tryInt =                                                    // <2>
	given Int = 10
	val x = trySummonFrom()
	print(x)
	assert(x == "found int 10")

def tryString =                                                 // <3>
	given String = "foo"
	val x = trySummonFrom()
	print(x)
	assert(x == "found string foo")

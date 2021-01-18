// src/script/scala/progscala3/contexts/ImplicitEvidence.scala

summon[Int <:< Int]
summon[Int <:< AnyVal]
summon[Int =:= Int]
summon[Int =:= AnyVal]                           // ERROR!

summon[(Int, String) <:< (Int, String)]
summon[(Int, String) <:< (AnyVal, AnyRef)]
summon[(Int, String) =:= (Int, String)]
summon[(Int, String) =:= (AnyVal, AnyRef)]       // ERROR!

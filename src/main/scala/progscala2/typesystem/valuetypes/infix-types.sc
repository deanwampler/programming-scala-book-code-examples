// src/main/scala/progscala2/typesystem/valuetypes/infix-types.sc

val xll1:  Int Either Double  Either String  = Left(Left(1))
val xll2: (Int Either Double) Either String  = Left(Left(1))

val xlr1:  Int Either Double  Either String  = Left(Right(3.14))
val xlr2: (Int Either Double) Either String  = Left(Right(3.14))

val xr1:   Int Either Double  Either String  = Right("foo")
val xr2:  (Int Either Double) Either String  = Right("foo")

val xl:   Int Either (Double Either String)  = Left(1)
val xrl:  Int Either (Double Either String)  = Right(Left(3.14))
val xrr:  Int Either (Double Either String)  = Right(Right("bar"))

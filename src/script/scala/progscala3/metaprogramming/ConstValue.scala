// src/script/scala/progscala3/metaprogramming/ConstValue.scala

import compiletime.constValue

assert(constValue[1] == 1)
assert(constValue["foo"] == "foo")

// src/script/scala/progscala3/objectsystem/variance/FunctionVariance.scala

class CSuper                                               // <1>
class C      extends CSuper
class CSub   extends C

val f1: C => C = (c: C)      => C()                        // <2>
val f2: C => C = (c: CSuper) => CSub()
val f3: C => C = (c: CSuper) => C()
val f4: C => C = (c: C)      => CSub()
// Compilation errors!
// val f5: C => C = (c: CSub)   => CSuper()                // <3>
// val f6: C => C = (c: CSub)   => C()
// val f7: C => C = (c: C)      => CSuper()

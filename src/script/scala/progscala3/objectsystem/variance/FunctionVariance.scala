// src/script/scala/progscala3/objectsystem/variance/FunctionVariance.scala

class CSuper                                               // <1>
class C      extends CSuper
class CSub   extends C

val f1: C => C = (c: C)      => new C                    // <2>
val f2: C => C = (c: CSuper) => new CSub
val f3: C => C = (c: CSuper) => new C
val f4: C => C = (c: C)      => new CSub
// Compilation errors!
// val f5: C => C = (c: CSub)   => new CSuper            // <3>
// val f6: C => C = (c: CSub)   => new C
// val f7: C => C = (c: C)      => new CSuper

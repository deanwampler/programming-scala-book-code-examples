// src/main/scala/progscala2/objectsystem/variance/func.scX

class CSuper                { def msuper() = println("CSuper") }       // <1>
class C      extends CSuper { def m()      = println("C") }
class CSub   extends C      { def msub()   = println("CSub") }

var f: C => C = (c: C)      => new C             // <2>
    f         = (c: CSuper) => new CSub          // <3>
    f         = (c: CSuper) => new C             // <4>
    f         = (c: C)      => new CSub          // <5>
    f         = (c: CSub)   => new CSuper        // <6> COMPILATION ERROR!

// code-examples/TypeSystem/variances/func-script.scala
// WON'T COMPILE

class CSuper                { def msuper = println("CSuper") }
class C      extends CSuper { def m      = println("C") }
class CSub   extends C      { def msub   = println("CSub") }

var f: C => C = (c: C)      => new C       // #1
    f         = (c: CSuper) => new CSub    // #2
    f         = (c: CSuper) => new C       // #3
    f         = (c: C)      => new CSub    // #4
    f         = (c: CSub)   => new CSuper  // #5: ERROR!

// code-examples/TypeSystem/variances/func2-script.scala
// WON'T COMPILE

class CSuper                { def msuper = println("CSuper") }
class C      extends CSuper { def m      = println("C") }
class CSub   extends C      { def msub   = println("CSub") }

def useF(f: C => C) = {
  val c1 = new C     // #1
  val c2: C = f(c1)  // #2
  c2.msuper          // #3
  c2.m               // #4
}

useF((c: C)      => new C)        // #5
useF((c: CSuper) => new CSub)     // #6
useF((c: CSub)   => {println(c.msub); new CSuper})   // #7: ERROR!

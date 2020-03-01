// src/error/scala/progscala3/metaprogramming/Func.scala
import scala.reflect.runtime.universe.typeOf

class CSuper                { def msuper() = println("CSuper") }
class C      extends CSuper { def m()      = println("C") }
class CSub   extends C      { def msub()   = println("CSub") }

assert(typeOf[C      => C     ] =:= typeOf[C => C] == true)  // <1>
assert(typeOf[CSuper => CSub  ] =:= typeOf[C => C] == false)
assert(typeOf[CSub   => CSuper] =:= typeOf[C => C] == false)

assert(typeOf[C      => C     ] <:< typeOf[C => C] == true)  // <2>
assert(typeOf[CSuper => CSub  ] <:< typeOf[C => C] == true)  // <3>
assert(typeOf[CSub   => CSuper] <:< typeOf[C => C] == false) // <4>

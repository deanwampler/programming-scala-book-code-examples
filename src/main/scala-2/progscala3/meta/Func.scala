// src/main/scala-2/progscala3/meta/Func.scala

package progscala3.meta

import scala.reflect.runtime.universe.typeOf

/*
 * This example uses the typeOf[...] feature that doesn't exist
 * in Scala 3.
 */

class CSuper                { def msuper() = println("CSuper") }
class C      extends CSuper { def m()      = println("C") }
class CSub   extends C      { def msub()   = println("CSub") }

assert(typeOf[C      => C     ] =:= typeOf[C => C] == true)  // <1>
assert(typeOf[CSuper => CSub  ] =:= typeOf[C => C] == false)
assert(typeOf[CSub   => CSuper] =:= typeOf[C => C] == false)

assert(typeOf[C      => C     ] <:< typeOf[C => C] == true)  // <2>
assert(typeOf[CSuper => CSub  ] <:< typeOf[C => C] == true)  // <3>
assert(typeOf[CSub   => CSuper] <:< typeOf[C => C] == false) // <4>

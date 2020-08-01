// src/main/scala/progscala3/objectsystem/variance/FunctionVariance.scala
package progscala3.objectsystem.variance

object FunctionVariance:

	// Old syntax used to layout code for easier comparison:
	class CSuper                { def msuper() = println("CSuper") }
	class C      extends CSuper { def m()      = println("C") }
	class CSub   extends C      { def msub()   = println("CSub") }

	def main(args: Array[String]): Unit =
		val f1: C => C = (c: C)      => new C
		val f2: C => C = (c: CSuper) => new CSub
		val f3: C => C = (c: CSuper) => new C
		val f4: C => C = (c: C)      => new CSub
		// Compilation error!
		// val f6: C => C = (c: CSub)   => new CSuper

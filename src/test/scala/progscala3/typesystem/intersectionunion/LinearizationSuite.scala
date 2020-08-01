// src/test/scala/progscala3/typesystem/intersectionunion/LinearizationSuite.scala
package progscala3.typesystem.intersectionunion

import munit._

/**
 * Test the example "AbbrevList".
 * Not very comprehensive... Should be converted to a ScalaCheck suite.
 */
class LinearizationSuite extends FunSuite:

	test("Declaration order of traits specifies precedence") {
		val c12 = new C with T1 with T2
		val c21 = new C with T2 with T1

		assert(c12.m("hello") == "( [ { hello } ] )")
		assert(c21.m("hello") == "[ ( { hello } ) ]")
	}

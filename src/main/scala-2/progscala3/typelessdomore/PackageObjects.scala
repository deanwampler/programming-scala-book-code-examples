// src/main/scala-2/progscala3/typelessdomore/PackageObjects.scala
package progscala3.typelessdomore   // Notice, no ".api"

package object api {
	val DEFAULT_COUNT = 5
	def countTo(limit: Int = DEFAULT_COUNT) = (0 to limit).foreach(println)

	class Class1 {
	  def m = "cm1"
	}

	object Object1 {
	  def m = "om1"
	}
}
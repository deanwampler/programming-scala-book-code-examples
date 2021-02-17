// src/test/scala/progscala3/basicoop/people/ZipCodePropertiesSuite.scala
package progscala3.basicoop.people

import munit.ScalaCheckSuite
import org.scalacheck.*

/** ScalaCheck example driven by MUnit .*/
class ZipCodePropertiesSuite extends ScalaCheckSuite:
  import Prop.forAll

  def validInts = for
    z <- Gen.choose(10000, 99999)
    e <- Gen.choose(1000,  9999)
  yield (z, e)

  property("succeeds with zip integers between 1-99999 and extensions between 0-9999") {
    forAll(validInts) { case (zipInt: Int, extInt: Int) =>
      val c = ZipCode(zipInt, extInt)
      c.zip == zipInt && c.extension == extInt
    }
  }

  property("succeeds with zip integers between 1-99999 and extension 0") {
    forAll(validInts) { case (zipInt: Int, _: Int) =>
      val c = ZipCode(zipInt, 0)
      c.zip == zipInt && c.extension == 0
    }
  }

  property("succeeds with zip integers between 1-99999 and no extension") {
    forAll(validInts) { case (zipInt: Int, _: Int) =>
      val c = ZipCode(zipInt)
      c.zip == zipInt && c.extension == 0
    }
  }

  property("succeeds with zip string between 1-99999 and extensions between 0-9999") {
    forAll(validInts) { case (zipInt: Int, extInt: Int) =>
      val c = ZipCode(zipInt.toString, extInt.toString)
      c.zip == zipInt && c.extension == extInt
    }
  }

  property("succeeds with zip string between 1-99999 and extension \"0\"") {
    forAll(validInts) { case (zipInt: Int, _: Int) =>
      val c = ZipCode(zipInt.toString, "0")
      c.zip == zipInt && c.extension == 0
    }
  }

  property("succeeds with zip string between 1-99999 and extension \"\"") {
    forAll(validInts) { case (zipInt: Int, _: Int) =>
      val c = ZipCode(zipInt.toString, "")
      c.zip == zipInt && c.extension == 0
    }
  }

  property("succeeds with zip string between 1-99999 and no extension") {
    forAll(validInts) { case (zipInt: Int, _: Int) =>
      val c = ZipCode(zipInt.toString)
      c.zip == zipInt && c.extension == 0
    }
  }
end ZipCodePropertiesSuite

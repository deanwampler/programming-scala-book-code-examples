// src/main/scala/progscala3/basicoop/people/ZipCodeProperties.scala
package progscala3.basicoop.people
import org.scalatest.FunSpec
import org.scalatestplus.scalacheck.ScalaCheckDrivenPropertyChecks
import org.scalacheck.Gen
import scala.language.implicitConversions
import org.scalactic.source.Position.here

class ZipCodeProperties extends FunSpec with ScalaCheckDrivenPropertyChecks {

  def validInts = for {
    z <- Gen.choose(10000, 99999)
    e <- Gen.choose(1000,  9999)
  } yield (z, e)

  describe ("ZipCode construction") {
    it ("succeeds with zip integers between 1-99999 and extensions between 0-9999") {
      forAll(validInts) { case (zipInt: Int, extInt: Int) =>
        val c = ZipCode(zipInt, extInt)
        assert(c.zip === zipInt)
        assert(c.extension === extInt)
      }
    }

    it ("succeeds with zip integers between 1-99999 and extension 0") {
      forAll(validInts) { case (zipInt: Int, _: Int) =>
        val c = ZipCode(zipInt, 0)
        assert(c.zip === zipInt)
        assert(c.extension === 0)
      }
    }

    it ("succeeds with zip integers between 1-99999 and no extension") {
      forAll(validInts) { case (zipInt: Int, _: Int) =>
        val c = ZipCode(zipInt)
        assert(c.zip === zipInt)
        assert(c.extension === 0)
      }
    }

    it ("succeeds with zip string between 1-99999 and extensions between 0-9999") {
      forAll(validInts) { case (zipInt: Int, extInt: Int) =>
        val c = ZipCode(zipInt.toString, extInt.toString)
        assert(c.zip === zipInt)
        assert(c.extension === extInt)
      }
    }

    it ("foo") {
      (10000 until 100000) foreach {i =>
        val c = ZipCode(i.toString, "0")
        assert(c.zip === i)
        assert(c.extension === 0)
      }
    }

    it ("succeeds with zip string between 1-99999 and extension \"0\"") {
      forAll(validInts) { case (zipInt: Int, _: Int) =>
        val c = ZipCode(zipInt.toString, "0")
        assert(c.zip === zipInt)
        assert(c.extension === 0)
      }
    }

    it ("succeeds with zip string between 1-99999 and extension \"\"") {
      forAll(validInts) { case (zipInt: Int, _: Int) =>
        val c = ZipCode(zipInt.toString, "")
        assert(c.zip === zipInt)
        assert(c.extension === 0)
      }
    }

    it ("succeeds with zip string between 1-99999 and no extension") {
      forAll(validInts) { case (zipInt: Int, _: Int) =>
        val c = ZipCode(zipInt.toString)
        assert(c.zip === zipInt)
        assert(c.extension === 0)
      }
    }
  }
}

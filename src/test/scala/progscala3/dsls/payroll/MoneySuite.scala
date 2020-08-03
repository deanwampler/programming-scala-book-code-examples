// src/test/scala/progscala3/dsls/payroll/internal/MoneySuite.scala
package progscala3.dsls.payroll

import progscala3.dsls.payroll.dsc
import munit.FunSuite
import org.scalacheck._
import scala.language.postfixOps

/**
 * ScalaCheck example driven by MUnit
 * TODO: Really this should be a "full" ScalaCheck properties test.
 */

class MoneySuite extends FunSuite:

  val seq1 = Seq("00","10","25","50")

  test("Percentage.toString returns the value with % appended") {
    for
      i <- seq1
      j <- seq1
      d = i.toDouble + (j.toDouble/100.0)
    do assert(s"${i.toInt}.$j%" == Percentage(d).toString)
  }

  test("Extension method Double.percent returns a Percentage") {
    for
      i <- seq1
      j <- seq1
      d = i.toDouble + (j.toDouble/100.0)
    do assert(d.percent == Percentage(d))
  }

  test("Dollars can be constructed with a $\"MM.NN\" string") {
    for
      i <- seq1
      j <- seq1
      d = i.toDouble + (j.toDouble/100.0)
    do assert($"${i.toInt}.$j" == Dollars(d))
  }

  test("Dollars.toString returns the value with $ prepended") {
    for
      i <- seq1
      j <- seq1
      d = i.toDouble + (j.toDouble/100.0)
    do assert(s"$$${i.toInt}.$j" == Dollars(d).toString, s"$$$i.$j =?= ${Dollars(d)}")
  }

  test("Extension method Double.dollars returns a Dollars") {
    for
      i <- seq1
      j <- seq1
      d = i.toDouble + (j.toDouble/100.0)
    do assert(d.dollars == Dollars(d))
  }

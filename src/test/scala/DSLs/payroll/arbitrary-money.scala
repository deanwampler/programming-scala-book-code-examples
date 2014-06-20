// src/test/scala/DSLs/payroll/arbitrary-money.scala

package dsls.payroll

import org.scalacheck._
import org.scalacheck.Prop._

trait ArbitraryMoney {
  def toD(i: Int) = i * 10.0
  
  implicit def arbitraryMoney: Arbitrary[Money] = Arbitrary {
    Gen.sized {s => 
      for { 
        whole   <- Gen.choose(-s, s) 
        decimal <- Gen.choose(-s, s) 
      } yield Money(toD(whole) + decimal * 0.11)
    }
  }
}

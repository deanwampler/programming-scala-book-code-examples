// code-examples/DSLs/payroll/paycheck-spec.scala
package dsls.payroll

import org.scalatest.{ FunSpec, ShouldMatchers } 
import org.scalatest.prop.Checkers
import org.scalacheck.{Arbitrary, Gen}
import org.scalacheck.Prop._

class PaycheckSpec extends FunSpec with ShouldMatchers 
  with Checkers with ArbitraryMoney { 

  implicit def arbitraryPaycheck: Arbitrary[Paycheck] = Arbitrary {
    Gen.sized {s => 
      for { 
        gross <- Arbitrary.arbitrary[Money]
        deductions <- Arbitrary.arbitrary[Money]
        if (gross >= deductions)
      } yield Paycheck(gross, gross - deductions, deductions)
    }
  }
  
  describe ("Paycheck") {
    it ("computes gross pay") {
      check ((p: Paycheck, m: Money) => 
        (p plusGross m) == Paycheck(p.gross + m, p.net + m, p.deductions))
    }
  
    it ("computes deductions") { 
      check ((p: Paycheck, m: Money) => 
        (p plusDeductions m) == Paycheck(p.gross, p.net - m, p.deductions + m))
    }
  }
}

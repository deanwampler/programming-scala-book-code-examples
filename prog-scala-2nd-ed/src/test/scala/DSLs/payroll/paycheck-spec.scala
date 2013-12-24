// code-examples/DSLs/payroll/paycheck-spec.scala
package payroll

import org.scalatest.{ FunSpec, ShouldMatchers } 
 
import org.scalacheck._
import org.scalacheck.Prop._

class PaycheckSpec extends FunSpec with ShouldMatchers("Paycheck") 
        with ScalaCheck with ArbitraryMoney { 

    implicit def arbitraryPaycheck: Arbitrary[Paycheck] = Arbitrary {
        Gen.sized {s => 
            for { 
                gross <- Arbitrary.arbitrary[Money]
                deductions <- Arbitrary.arbitrary[Money]
                if (gross >= deductions)
            } yield Paycheck(gross, gross - deductions, deductions)
        }
    }
    
    "Paycheck.plusGross" verifies { 
        (p: Paycheck, m: Money) => (p plusGross m) == 
            Paycheck(p.gross + m, p.net + m, p.deductions)
    }
    "Paycheck.plusDeductions" verifies { 
        (p: Paycheck, m: Money) => (p plusDeductions m) == 
            Paycheck(p.gross, p.net - m, p.deductions + m)
    }
}

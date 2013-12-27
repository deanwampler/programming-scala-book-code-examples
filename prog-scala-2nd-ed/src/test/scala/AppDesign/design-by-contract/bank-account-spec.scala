// src/test/scala/AppDesign/design-by-contract/bank-account-spec.scala

package appdesign.designbycontract

import org.scalatest.{ FunSpec, ShouldMatchers } 
 
class BankAccountSpec extends FunSpec with ShouldMatchers { 
  describe ("Creating an account with a negative balance") {
    it ("fail because the initial balance must be positive.") {
      intercept[IllegalArgumentException] { new BankAccount(-100.0) }
    }  
  } 

  describe ("Debiting an account") {
    it ("fail if the debit amount is < 0") {
      val account = new BankAccount(100.0)
      intercept[IllegalArgumentException] { account.debit(-10.0) }
    }
  }
  
  describe ("Debiting an account") {
    it ("fail if the debit amount is > the balance") {
      val account = new BankAccount(100.0)
      intercept[AssertionError] { account.debit(110.0) }
    }
  }    
}

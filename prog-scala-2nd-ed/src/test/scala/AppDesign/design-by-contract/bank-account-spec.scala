// code-examples/AppDesign/design-by-contract/bank-account-spec.scala

import org.specs2.mutable._ 

object BankAccountSpec extends Specification { 
  "Creating an account with a negative balance" should {
    "fail because the initial balance must be positive." in {
      new BankAccount(-100.0) must throwAn[IllegalArgumentException]
    }  
  } 

  "Debiting an account" should {
    "fail if the debit amount is < 0" in {
      val account = new BankAccount(100.0)
      (account.debit(-10.0)) must throwAn[IllegalArgumentException]
    }
  }
  
  "Debiting an account" should {
    "fail if the debit amount is > the balance" in {
      val account = new BankAccount(100.0)
      (account.debit(110.0)) must throwAn[AssertionError]
    }
  }    
}

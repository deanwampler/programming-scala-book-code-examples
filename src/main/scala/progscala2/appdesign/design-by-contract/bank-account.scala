// src/main/scala/progscala2/appdesign/design-by-contract/bank-account.scala

package progscala2.appdesign.designbycontract

class BankAccount(val balance: Double) {
  require(balance >= 0.0)
  def debit(amount: Double) = {
    require(amount > 0.0, "The debit amount must be > 0.0")
    assume(balance - amount > 0.0, "Overdrafts are not permitted")
    new BankAccount(balance - amount)
  }
  def credit(amount: Double) = {
    require(amount > 0.0, "The credit amount must be > 0.0")
    new BankAccount(balance + amount)
  }
}

// src/main/scala/progscala3/appdesign/dbc/BankAccount.scala
package progscala3.appdesign.dbc

import scala.annotation.targetName

case class Money(val amount: Double):                                // <1>
  require(amount >= 0.0, s"Negative amount $amount not allowed")

  @targetName("plus")  def +  (m: Money): Money = Money(amount + m.amount)
  @targetName("minus") def -  (m: Money): Money = Money(amount - m.amount)
  @targetName("ge")    def >= (m: Money): Boolean = amount >= m.amount

  override def toString = "$"+amount

case class BankAccount(balance: Money):
  def debit(amount: Money) =                                         // <2>
    require(balance >= amount,
      s"Overdrafts are not permitted, balance = $balance, debit = $amount")
    (BankAccount(balance - amount)).ensuring(
      newBA => newBA.balance == this.balance - amount)

  def credit(amount: Money) = BankAccount(balance + amount)          // <3>

import scala.util.Try

@main def TryBankAccount: Unit =
  Seq(-10, 0, 10) foreach (i => println(f"$i%3d: ${Try(Money(i.toDouble))}"))

  val ba1 = BankAccount(Money(10.0))
  val ba2 = ba1.credit(Money(5.0))
  val ba3 = ba2.debit(Money(8.5))
  val ba4 = Try(ba3.debit(Money(10.0)))

  println(s"""
    |Initial state: $ba1
    |After credit of $$5.0: $ba2
    |After debit of $$8.5: $ba3
    |After debit of $$10.0: $ba4""".stripMargin)

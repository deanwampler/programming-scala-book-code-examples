// src/script/scala/progscala3/fp/datastructs/FlatMapValidate.scala

case class Account(name: String, password: String, age: Int)  // <1>

val validName: Account => Option[Account] =                   // <2>
  a => if a.name.length > 0 then Some(a) else None

val validPwd: Account => Option[Account] =
  a => if a.password.length > 0 then Some(a) else None

val validAge: Account => Option[Account] =
  a => if a.age >= 18 then Some(a) else None                   // <3>

val accounts = Seq(
  Account("bucktrends", "1234", 17),
  Account("", "1234", 29),
  Account("bucktrends", "", 29),
  Account("bucktrends", "1234", 29))

val validated = accounts.map { account =>                     // <4>
  Some(account).flatMap(validName).flatMap(validPwd).flatMap(validAge)
}

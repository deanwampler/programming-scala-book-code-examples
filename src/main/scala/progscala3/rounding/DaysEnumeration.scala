// src/main/scala/progscala3/rounding/DaysEnumeration.scala

package progscala3.rounding

object WeekDay extends Enumeration {
  type WeekDay = Value
  val Mon, Tue, Wed, Thu, Fri, Sat, Sun = Value
  
  def isWorkingDay(d: WeekDay): Boolean = ! (d == Sat || d == Sun)
  def upper(d: WeekDay): String = d.toString.toUpperCase
  def lower(d: WeekDay): String = d.toString.toLowerCase
}

/** Assing labels to each value. */
object WeekDay2 extends Enumeration {
  type WeekDay2 = Value
  val Mon = Value("Monday")
  val Tue = Value("Tuesday")
  val Wed = Value("Wednesday")
  val Thu = Value("Thursday")
  val Fri = Value("Friday")
  val Sat = Value("Saturday")
  val Sun = Value("Sunday")
  
  def isWorkingDay(d: WeekDay2): Boolean = ! (d == Sat || d == Sun)
  def upper(d: WeekDay2): String = d.toString.toUpperCase
  def lower(d: WeekDay2): String = d.toString.toLowerCase
}

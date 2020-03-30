// src/main/scala/progscala3/rounding/DaysEnumerationWithLabels.scala

package progscala3.rounding

/** Assing labels to each value. */
object WeekDayWithLabel extends Enumeration {
  type WeekDayWithLabel = Value
  val Mon = Value("Monday")
  val Tue = Value("Tuesday")
  val Wed = Value("Wednesday")
  val Thu = Value("Thursday")
  val Fri = Value("Friday")
  val Sat = Value("Saturday")
  val Sun = Value("Sunday")

  def isWorkingDay(d: WeekDayWithLabel): Boolean = ! (d == Sat || d == Sun)
  def upper(d: WeekDayWithLabel): String = d.toString.toUpperCase
  def lower(d: WeekDayWithLabel): String = d.toString.toLowerCase
}

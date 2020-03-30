// src/main/scala/progscala3/rounding/DaysEnumeration.scala

package progscala3.rounding

object WeekDay extends Enumeration {                               // <1>
  type WeekDay = Value                                             // <2>
  val Mon, Tue, Wed, Thu, Fri, Sat, Sun = Value                    // <3>

  def isWorkingDay(d: WeekDay): Boolean = ! (d == Sat || d == Sun) // <4>
  def upper(d: WeekDay): String = d.toString.toUpperCase
  def lower(d: WeekDay): String = d.toString.toLowerCase
}

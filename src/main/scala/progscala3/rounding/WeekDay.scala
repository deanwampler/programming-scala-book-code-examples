// src/main/scala/progscala3/rounding/WeekDay.scala
package progscala3.rounding

/**
 * This file reproduces the WeekDay definition in DaysEnumeration. This file
 * isn't included in the book, but subsequent examples import this definition.
 */
enum WeekDay(val fullName: String):
  case Sun extends WeekDay("Sunday")
  case Mon extends WeekDay("Monday")
  case Tue extends WeekDay("Tuesday")
  case Wed extends WeekDay("Wednesday")
  case Thu extends WeekDay("Thursday")
  case Fri extends WeekDay("Friday")
  case Sat extends WeekDay("Saturday")

  def isWorkingDay: Boolean = ! (this == Sat || this == Sun)

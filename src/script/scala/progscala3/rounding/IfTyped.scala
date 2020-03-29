// src/script/scala/progscala3/rounding/IfTyped.scala

import progscala3.rounding.WeekDay
import progscala3.rounding.WeekDay._

val days = WeekDay.values.toSeq map { day =>
  if WeekDay.isWorkingDay(day) then day
  else if day == WeekDay.Sat then "Saturday"
  else 0
}

assert(days == Seq[Any](Mon, Tue, Wed, Thu, Fri, "Saturday", 0))
// src/script/scala-2/progscala3/rounding/IfTyped.scala

import progscala3.rounding.WeekDay
import progscala3.rounding.WeekDay.*

val days = WeekDay.values.toSeq map { day =>
  if (WeekDay.isWorkingDay(day)) day
  else if (day == WeekDay.Sat) "Saturday"
  else 0
}

assert(days == Seq[Any](Mon, Tue, Wed, Thu, Fri, "Saturday", 0))

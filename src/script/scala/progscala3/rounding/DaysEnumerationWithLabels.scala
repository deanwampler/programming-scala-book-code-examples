// src/script/scala/progscala3/rounding/DaysEnumerationWithLabel.scala

import progscala3.rounding.WeekDayWithLabel

assert(WeekDayWithLabel.values.toSeq.map(_.toString) == Seq(
  "Monday", "Tuesday", "Wednesday", "Thursday",
  "Friday", "Saturday", "Sunday"))

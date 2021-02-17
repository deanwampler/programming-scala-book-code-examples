// src/script/scala-2/progscala3/rounding/ScopedOptionFor.scala

import progscala3.rounding.WeekDay
import progscala3.rounding.WeekDay.*

val dayOptions = Seq(
  Some(Mon), None, Some(Tue), Some(Wed), None,
  Some(Thu), Some(Fri), Some(Sat), Some(Sun), None)

val goodDays1 = for {         // First pass
  dayOpt <- dayOptions
  day <- dayOpt
  fn   = day.fullName
} yield fn
assert(goodDays1 ==
  Seq("Monday", "Tuesday", "Wednesday", "Thursday", "Friday",
      "Saturday", "Sunday"))

val goodDays2 = for {         // second, more concise pass
  case Some(day) <- dayOptions
  fn = day.fullName
} yield fn
assert(goodDays2 ==
  Seq("Monday", "Tuesday", "Wednesday", "Thursday", "Friday",
      "Saturday", "Sunday"))

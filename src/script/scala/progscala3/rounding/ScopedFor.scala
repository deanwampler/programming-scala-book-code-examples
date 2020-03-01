// src/script/scala/progscala3/rounding/ScopedFor.scala

import progscala3.rounding.WeekDay

for {
  day <- WeekDay.values
  up   = WeekDay.upper(day)
} println(up)

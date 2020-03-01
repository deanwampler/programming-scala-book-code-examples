// src/main/scala/progscala3/rounding/ScopedFor.sc

import progscala3.rounding.WeekDay

for {
  day <- WeekDay.values
  up   = WeekDay.upper(day)
} println(up)

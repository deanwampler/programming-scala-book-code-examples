// src/main/scala/progscala2/fp/basics/hofs-example.sc

(1 to 10) filter (_ % 2 == 0) map (_ * 2) reduce (_ * _)


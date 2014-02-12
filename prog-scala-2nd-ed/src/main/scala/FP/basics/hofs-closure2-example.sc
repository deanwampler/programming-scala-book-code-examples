// src/main/scala/FP/basics/hofs-closure-example.sc

def m1 (multiplier: Int => Int) = {
  (1 to 10) filter (_ % 2 == 0) map multiplier reduce (_ * _)
}

def m2 = {
  var factor = 2
  val multiplier = (i: Int) => i * factor

  println(s"$factor => ${m1(multiplier)}")

  factor = 3
  println(s"$factor => ${m1(multiplier)}")
}

m2

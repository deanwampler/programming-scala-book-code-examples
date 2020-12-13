// src/script/scala/progscala3/fp/basics/Tupling.scala

def mult(d1: Double, d2: Double) = d1 * d2

val d23 = (2.2, 3.3)
val d = mult(d23._1, d23._2)

val multTupled1 = Function.tupled(mult)
val multTupled2 = mult.tupled
val d21 = multTupled1(d23)
val d22 = multTupled2(d23)

val mult2 = Function.untupled(multTupled1)
val d3 = mult2(d23._1, d23._2)

val mult2b = multTupledb.untupled  // ERROR
// val d3b = mult2b(d23._1, d23._2)

val f22 = (x1: Int, x2: Int, x3: Int, x4: Int, x5: Int,
  x6: Int, x7: Int, x8: Int, x9: Int, x10: Int,
  x11: Int, x12: Int, x13: Int, x14: Int, x15: Int,
  x16: Int, x17: Int, x18: Int, x19: Int, x20: Int,
  x21: Int, x22: Int) => x1 + x2 + x3 + x4 + x5 +
    x6 + x7 + x8 + x9 + x10 + x11 + x12 + x13 + x14 + x15 +
    x16 + x17 + x18 + x19 + x20 + x21 + x22
val t22 = (1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15,
  16, 17, 18, 19, 20, 21, 22)
val tf22 = Function.tupled(f22)
tf22(t22)
val f22b = tf22.untupled(tf22)  // ERROR
// f22b(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15,
//   16, 17, 18, 19, 20, 21, 22)

val f23 = (x1: Int, x2: Int, x3: Int, x4: Int, x5: Int,
  x6: Int, x7: Int, x8: Int, x9: Int, x10: Int,
  x11: Int, x12: Int, x13: Int, x14: Int, x15: Int,
  x16: Int, x17: Int, x18: Int, x19: Int, x20: Int,
  x21: Int, x22: Int, x23: Int) => x1 + x2 + x3 + x4 + x5 +
    x6 + x7 + x8 + x9 + x10 + x11 + x12 + x13 + x14 + x15 +
    x16 + x17 + x18 + x19 + x20 + x21 + x22 + x23
val t23 = (1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15,
  16, 17, 18, 19, 20, 21, 22, 23)
val tf23 = f23.tupled   // ERROR
// tf23(t23)

/** Creates a tupled version of this function: instead of N arguments,
  *  it accepts a single [[scala.Tuple]] with N elements as argument.
  *
  *  This is a generalization of [[scala.FunctionN.tupled]] that work on functions of any arity
  *  (Adapted from https://dotty.epfl.ch/docs/reference/other-new-features/tupled-function.html)
  *  @tparam F the function type
  *  @tparam Args the tuple type with the same types as the function arguments of F
  *  @tparam R the return type of F
  */
extension [F, Args <: Tuple, R](f: F):
  def tupled (using tf: TupledFunction[F, Args => R]): Args => R =
    tf.tupled(f)
extension [F, Args <: Tuple, R](f: Args => R):
  def untupled(using tf: TupledFunction[F, Args => R]): F = tf.untupled(f)

val f22b = tf22.untupled     // Now works!
f22b(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15,
  16, 17, 18, 19, 20, 21, 22)

val tf23 = f23.tupled        // Now works!
tf23(t23)
val f23b = tf23.untupled     // Now works!
f23b(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15,
  16, 17, 18, 19, 20, 21, 22, 23)


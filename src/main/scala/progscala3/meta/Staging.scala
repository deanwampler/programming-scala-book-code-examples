// src/main/scala/progscala3/meta/Staging.scala
package progscala3.meta
import scala.quoted.*
import scala.quoted.staging.*                                   // <1>

object Fold:
  given Compiler = Compiler.make(getClass.getClassLoader)       // <2>

  /**
   * Fold operation:
   * @param operation for folding, + or *
   * @param the seed value
   * @param the array to fold.
   */
  val f: (String, Int, Array[Int]) => Int = run {               // <3>
    val stagedFold: Expr[(String, Int, Array[Int]) => Int] = '{
      (op: String, seed: Int, arr: Array[Int]) =>
        val combine = if op == "*" then (x:Int, y:Int) => x*y   // <4>
          else (x:Int, y:Int) => x+y
        ${ fold[Int]('seed, 'arr)('combine) }
    }
    println(s"\nStaged fold code after expansion:\n\n${stagedFold.show}")
    stagedFold                                                  // <5>
  }

  def fold[T](seed: Expr[T], arr: Expr[Array[T]])(              // <6>
      combine: Expr[(T,T) => T])(
      using Type[T], Quotes): Expr[T] = '{
    var accum: T = ($seed)
    var i = 0
    while i < ($arr).length do {
      val element: T = ($arr)(i)
      i += 1
      accum = ${combine}(accum, element)
    }
    accum
  }

@main def TryStaging(operator: String, seed: Int, args: Int*) = // <7>

  val result = Fold.f(operator, seed, args.toArray)
  println(s"fold of ($args) with operator $operator and seed $seed: $result")


// src/main/scala/progscala2/concurrency/futures/future-fold.sc
import scala.concurrent.{Await, Future}
import scala.concurrent.duration.Duration
import scala.concurrent.ExecutionContext.Implicits.global

val futures = (0 to 9) map {                                         // <1>
  i => Future { 
    val s = i.toString                                               // <2>
    print(s)
    s
  }
}
 
val f = Future.reduceLeft(futures)((s1, s2) => s1 + s2)              // <3>
println(f) 

val n = Await.result(f, Duration.Inf)                                // <4>
assert(n == "0123456789")

// tag::scala2[]
// src/script/scala/progscala3/contexts/ImplicitParams2ContextFunctions.scala

import scala.concurrent.{Await, ExecutionContext, Future}
import scala.concurrent.duration.*

val sameThreadExecutionContext = new ExecutionContext:          // <1>
  def execute(runnable: Runnable): Unit =
    printf("start > ")
    runnable.run()
    printf("finish > ")
  def reportFailure(cause: Throwable): Unit =
    println(s"sameThreadExecutionContext failure: $cause")

object AsyncRunner2:
  def apply[T](body: ExecutionContext => Future[T]): T =        // <2>
    val future = body(sameThreadExecutionContext)
    Await.result(future, 2.seconds)

val result2 = AsyncRunner2 {                                    // <3>
  implicit executionContext =>
    Future(1).map(_ * 2).filter(_ > 0)
}
// end::scala2[]

// tag::scala3[]
object AsyncRunner3:
  type RunnerContext[T] = ExecutionContext ?=> Future[T]

  def apply[T](body: => RunnerContext[T]): T =                  // <1>
    given ExecutionContext = sameThreadExecutionContext
    Await.result(body, 2.seconds)

val result3 = AsyncRunner3 {                                    // <2>
  Future(1).map(_ * 2).filter(_ > 0)
}
// end::scala3[]

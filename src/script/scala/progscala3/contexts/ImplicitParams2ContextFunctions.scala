// src/script/scala/progscala3/contexts/ImplicitParams2ContextFunctions.scala

import scala.concurrent.{Await, ExecutionContext, Future}
import scala.concurrent.duration._

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
    Await.result(future, 2.second)

val result2 = AsyncRunner2 { implicit executionContext =>       // <3>
  Future(1).map(_ * 2).filter(_ > 0)
}


object AsyncRunner3:
  type RunnerContext[T] = ExecutionContext ?=> Future[T]

  def apply[T](body: => RunnerContext[T]): T =                  // <4>
    given ExecutionContext = sameThreadExecutionContext
    Await.result(body, 2.second)

val result3 = AsyncRunner3 {                                    // <5>
  Future(1).map(_ * 2).filter(_ > 0)
}


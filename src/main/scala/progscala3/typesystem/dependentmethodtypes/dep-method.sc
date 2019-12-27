// src/main/scala/progscala3/typesystem/dependentmethodtypes/dep-method.sc
import scala.concurrent.{Await, Future}
import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global

case class LocalResponse(statusCode: Int)
case class RemoteResponse(message: String)

sealed trait Computation {
  type Response
  val work: Future[Response]
}

case class LocalComputation(
    work: Future[LocalResponse]) extends Computation {
  type Response = LocalResponse
}
case class RemoteComputation(
    work: Future[RemoteResponse]) extends Computation {
  type Response = RemoteResponse
}

object Service {
  def handle(computation: Computation): computation.Response = {
    Await.result(computation.work, 5.seconds)
  }
}


println("LocalComputation:")
val lc = LocalComputation(Future(LocalResponse(0)))
Service.handle(lc)
// Result: LocalResponse = LocalResponse(0)

println("RemoteComputation:")
val rc = RemoteComputation(Future(RemoteResponse("remote call")))
println(rc)
Service.handle(rc)
// Result: RemoteResponse = RemoteResponse(remote call)

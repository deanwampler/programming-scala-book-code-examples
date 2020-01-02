// src/main/scala/progscala3/typelessdomore/futures.sc
import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.{Failure, Success}

def sleep(millis: Long) = {
  Thread.sleep(millis)
}

// Busy work ;)
def doWork(index: Int) = {
  sleep((math.random * 1000).toLong)
  index
}

(1 to 5) foreach { index =>
  val future = Future {
    doWork(index)
  }
  // Before Scala 2.13, you could use separate future.onSuccess
  // and future.onFailure callbacks. Those were deprecated in 2.12
  // and removed in 2.13, with future.onComplete replacing them.
  future onComplete {
    case Success(answer) => println(s"Success! returned: $answer")
    case Failure(th) => println(s"FAILURE! returned: $th")
  }
}

sleep(1000)  // Wait long enough for the "work" to finish.
println("Finito!")

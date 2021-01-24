// src/script/scala/progscala3/typelessdomore/Futures.scala
import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global            // <1>
import scala.util.{Failure, Success}

def sleep(millis: Long) = Thread.sleep(millis)                       // <2>

(1 to 5).foreach { i =>
  val future = Future {                                              // <3>
    val duration = (math.random * 1000).toLong
    sleep(duration)
    if i == 3 then throw RuntimeException(s"$i -> $duration")
    duration
  }
  future.onComplete {                                                // <4>
    case Success(result)    => println(s"Success! #$i -> $result")
    case Failure(throwable) => println(s"FAILURE! #$i -> $throwable")
  }
}
sleep(1000)  // Wait long enough for the "work" to finish.
println("Finished!")

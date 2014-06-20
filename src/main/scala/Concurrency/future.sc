// src/main/scala/Concurrency/future.sc
import scala.actors.Futures._

val eventually = future(5 * 42)
println(eventually())


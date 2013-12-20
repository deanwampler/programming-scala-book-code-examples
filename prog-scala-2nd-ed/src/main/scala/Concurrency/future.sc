// code-examples/Concurrency/future-script.scala
import scala.actors.Futures._

val eventually = future(5 * 42)
println(eventually())


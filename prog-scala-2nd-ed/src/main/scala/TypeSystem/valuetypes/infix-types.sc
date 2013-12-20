// code-examples/TypeSystem/valuetypes/infix-types-script.scala

def attempt(operation: => Boolean): Throwable Either Boolean = try {
  Right(operation)
} catch {
  case t: Throwable => Left(t)
}

println(attempt { throw new RuntimeException("Boo!") })
println(attempt { true })
println(attempt { false })

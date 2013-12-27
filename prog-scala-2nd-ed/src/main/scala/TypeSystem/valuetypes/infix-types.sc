// src/main/scala/TypeSystem/valuetypes/infix-types.sc

def attempt(operation: => Boolean): Throwable Either Boolean = try {
  Right(operation)
} catch {
  case t: Throwable => Left(t)
}

println(attempt { throw new RuntimeException("Boo!") })
println(attempt { true })
println(attempt { false })

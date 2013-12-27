// src/main/scala/TypeSystem/variances/mutable-type-variance.sc
// WON'T COMPILE: Mutable parameterized types can't have variance annotations

class ContainerPlus[+A](var value: A)      // ERROR
class ContainerMinus[-A](var value: A)     // ERROR

println( new ContainerPlus("Hello World!") )
println( new ContainerMinus("Hello World!") )

// src/main/scala/progscala3/objectsystem/variance/MutableTypeVariance.sc
// WON'T COMPILE: Parameterized types for vars can’t have variance annotations

class ContainerPlus[+A](var value: A)      // COMPILATION ERROR
class ContainerMinus[-A](var value: A)     // COMPILATION ERROR

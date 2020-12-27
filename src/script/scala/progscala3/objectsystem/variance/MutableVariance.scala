// tag::first[]
// src/script/scala/progscala3/objectsystem/variance/MutableVariance.scala

class Invariant[A](var mut: A)              // Okay
class Covariant[+A](var mut: A)             // ERROR
class Contravariant[-A](var mut: A)         // ERROR
// end::first[]

// tag::second[]
class Invariant[A](val mutInit: A):         // Okay
  private var _mut: A = mutInit
  def mut_=(a: A): Unit = _mut = a
  def mut: A = _mut
// end::second[]

// tag::third[]
class Covariant[+A](val mutInit: A):        // ERROR
  private var _mut: A = mutInit
  def mut_=(a: A): Unit = _mut = a
  def mut: A = _mut

class Contravariant[-A](val mutInit: A):    // ERROR
  private var _mut: A = mutInit
  def mut_=(a: A): Unit = _mut = a
  def mut: A = _mut
// end::third[]

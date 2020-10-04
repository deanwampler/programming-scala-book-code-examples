// tag::first[]
// src/script/scala/progscala3/objectsystem/variance/MutableVariance.scala

class CInvariant[A](var mut: A)
class CCovariant[+A](var mut: A)            // ERROR
class CContravariant[-A](var mut: A)        // ERROR
// end::first[]

// tag::cinvariant[]
class CInvariant[A](val mutInit: A):
  private var _mut: A = mutInit
  def mut_=(a: A): Unit = _mut = a
  def mut: A = _mut
// end::cinvariant[]

// tag::second[]
class CCovariant[+A](val mutInit: A):
  private var _mut: A = mutInit
  def mut_=(a: A): Unit = _mut = a
  def mut: A = _mut

class CContravariant[-A](val mutInit: A):
  private var _mut: A = mutInit
  def mut_=(a: A): Unit = _mut = a
  def mut: A = _mut
// end::second[]

val ci: CInvariant[C]

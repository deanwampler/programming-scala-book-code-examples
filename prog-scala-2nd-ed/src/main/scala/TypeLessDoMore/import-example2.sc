// code-examples/TypeLessDoMore/import-example2-script.scala

def writeAboutBigInteger() = {

  import java.math.BigInteger.{
    ONE => _,
    TEN,     
    ZERO => JAVAZERO }

  // ONE is effectively undefined
  // println( "ONE: "+ONE )
  println( "TEN: "+TEN )
  println( "ZERO: "+JAVAZERO )
}

writeAboutBigInteger()

// code-examples/BasicOOP/scoping/private-this-pkg-wont-compile.scala
// WON'T COMPILE

package scopeA {
  private[this] class PrivateClass1

  package scopeA2 {
    private[this] class PrivateClass2
  }

  class PrivateClass3 extends PrivateClass1  // ERROR
  protected class PrivateClass4 extends PrivateClass1 // ERROR
  private class PrivateClass5 extends PrivateClass1
  private[this] class PrivateClass6 extends PrivateClass1

  private[this] class PrivateClass7 extends scopeA2.PrivateClass2 // ERROR
}

package scopeB {
  class PrivateClass1B extends scopeA.PrivateClass1 // ERROR
}

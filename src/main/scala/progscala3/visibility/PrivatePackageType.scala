// src/main/scala/progscala3/visibility/PrivatePackageType.scala

package progscala3.visibility.privatepackagetype:

  package scopeA:
    private[scopeA] class PrivateClass1

    package scopeA2:
      private [scopeA2] class PrivateClass2
      private [scopeA]  class PrivateClass3

    class PrivateClass4 extends PrivateClass1
    protected class PrivateClass5 extends PrivateClass1
    private class PrivateClass6 extends PrivateClass1
    private[scopeA] class PrivateClass7 extends PrivateClass1

    // Scope error:
    // private[scopeA] class PrivateClass8 extends scopeA2.PrivateClass2
    private[scopeA] class PrivateClass9 extends scopeA2.PrivateClass3

  package scopeB {  // Use {} because we can't use scopeB: with no body.
    // Scope error:
    // class PrivateClass1B extends scopeA.PrivateClass1
  }
// src/main/scala/progscala3/visibility/PrivateTypeNested.scala

package progscala3.visibility.privatetypenested:

  package scopeA:
    class PrivateClass1:
      class Nested:
        private[PrivateClass1] val nestedField = 1

      private[PrivateClass1] val nested = Nested()
      val nestedNested = nested.nestedField

    // Use {} because we can't use X: with no body.
    class PrivateClass2 extends PrivateClass1 {
      // Scope error:
      // val nField = Nested().nestedField
    }

    class PrivateClass3:
      val privateClass1 = PrivateClass1()
      // Scope error:
      // val privateNField = privateClass1.nested.nestedField

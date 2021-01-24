// src/main/scala/progscala3/visibility/PrivateScope.scala

package progscala3.visibility.privatescope:

  package scopeA:
    class PrivateClass1(private val privateField1: Int) derives CanEqual:
      private val privateField2 = 1

      def equalFields(other: PrivateClass1) =
        (privateField1 == other.privateField1) &&
        (privateField2 == other.privateField2)
        // The following is a compilation error:
        // && (nested == other.nested)

      class Nested derives CanEqual:
        private val nestedField = 1

      private val nested = Nested()

    // Use {} because we can't use X: with no body.
    class PrivateClass2 extends PrivateClass1(1) {
      // All of the following are scope errors:
      // val field1 = privateField1
      // val field2 = privateField2
      // val nField = Nested().nestedField
    }

    class PrivateClass3:
      val privateClass1 = PrivateClass1(1)
      // All of the following are scope errors:
      // val privateField1 = privateClass1.privateField1
      // val privateField2 = privateClass1.privateField2
      // val privateNField = privateClass1.nested.nestedField

    private class PrivateClass4

    // These are scope errors:
    // class PrivateClass5 extends PrivateClass4
    // protected class PrivateClass6 extends PrivateClass4
    private class PrivateClass7 extends PrivateClass4

  package scopeB {
    // Scope error:
    // class PrivateClass4B extends scopeA.PrivateClass4
  }

// src/main/scala/progscala3/visibility/PrivateType.scala

package progscala3.visibility.privatetype:

  package scopeA:
    class PrivateClass1(private[PrivateClass1] val privateField1: Int) derives CanEqual:
      private[PrivateClass1] val privateField2 = 1

      def equalFields(other: PrivateClass1) =
        (privateField1 == other.privateField1) &&
        (privateField2 == other.privateField2)
        // The following is a compilation error:
        // && (nested == other.nested)

      class Nested derives CanEqual:
        private[Nested] val nestedField = 1

      private[PrivateClass1] val nested = Nested()
      // Scope error:
      // val nestedNested = nested.nestedField

    // Use {} because we can't use X: with no body.
    class PrivateClass2 extends PrivateClass1(1) {
      // All are scope errors:
      // val field1 = privateField1
      // val field2 = privateField2
      // val nField = Nested().nestedField
    }

    class PrivateClass3:
      val privateClass1 = PrivateClass1(1)
      // All are scope errors:
      // val privateField1 = privateClass1.privateField1
      // val privateField2 = privateClass1.privateField2
      // val privateNField = privateClass1.nested.nestedField

// src/main/scala/progscala3/visibility/ProtectedScope.scala

package progscala3.visibility.protectedscope:

  package scopeA:
    class ProtectedClass1(protected val protectedField1: Int) derives CanEqual:
      protected val protectedField2 = 1

      def equalFields(other: ProtectedClass1) =
        (protectedField1 == other.protectedField1) &&
        (protectedField2 == other.protectedField2)
        // The following is a compilation error:
        // && (nested == other.nested)

      class Nested derives CanEqual:
        protected val nestedField = 1

      protected val nested = Nested()

    class ProtectedClass2 extends ProtectedClass1(1):
      val field1 = protectedField1
      val field2 = protectedField2
      // Scope error:
      // val nField = Nested().nestedField

    class ProtectedClass3:
      val protectedClass1 = ProtectedClass1(1)
      // All are scope errors:
      // val protectedField1 = protectedClass1.protectedField1
      // val protectedField2 = protectedClass1.protectedField2
      // val protectedNField = protectedClass1.nested.nestedField

    protected class ProtectedClass4

    class ProtectedClass5 extends ProtectedClass4
    protected class ProtectedClass6 extends ProtectedClass4

  // Use {} because we can't use X: with no body.
  package scopeB {
    // Scope error:
    // class ProtectedClass4B extends scopeA.ProtectedClass4
  }

// src/main/scala/progscala3/visibility/PrivatePackage.scala

package progscala3.visibility.privatepackage:

  package scopeA:
    class PrivateClass1:
      private[scopeA] val privateField = 1

      class Nested:
        private[scopeA] val nestedField = 1
            private[scopeA] val nested = Nested()

    class PrivateClass2 extends PrivateClass1:
      val field  = privateField
      val nField = Nested().nestedField

    class PrivateClass3:
      val privateClass1 = PrivateClass1()
      val privateField  = privateClass1.privateField

    package scopeA2:
      class PrivateClass4:
        private[scopeA2] val field1 = 1
        private[scopeA]  val field2 = 2

    class PrivateClass5:
      val privateClass4 = scopeA2.PrivateClass4()
      // Scope error:
      // val field1 = privateClass4.field1
      val field2 = privateClass4.field2

  package scopeB:
    class PrivateClass1B extends scopeA.PrivateClass1:
      // Scope error:
      // val field1 = privateField
      val privateClass1 = scopeA.PrivateClass1()
      // Scope error:
      // val field2 = privateClass1.privateField

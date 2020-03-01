// src/main/scala/progscala3/visibility/ScopeInheritance.scala

package progscala3.visibility.scopeinheritance {

  package scopeA {
    class Class1 {
      private[scopeA]   val scopeA_privateField = 1
      protected[scopeA] val scopeA_protectedField = 2
      private[Class1]   val class1_privateField = 3
      protected[Class1] val class1_protectedField = 4
    }

    class Class2 extends Class1 {
      val field1 = scopeA_privateField
      val field2 = scopeA_protectedField
      // Scope error:
      // val field3 = class1_privateField
      val field4 = class1_protectedField
    }
  }

  package scopeB {
    class Class2B extends scopeA.Class1 {
      // Scope error:
      // val field1 = scopeA_privateField
      val field2 = scopeA_protectedField
      // Scope error:
      // val field3 = class1_privateField
      val field4 = class1_protectedField
    }
  }
}

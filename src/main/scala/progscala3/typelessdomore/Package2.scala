// src/main/scala/progscala3/typelessdomore/Package2.scala
package com:
  package example:                // Subpackage of "com"
    package pkg1:                 // Subpackage of "example"
      class Class11:              // Class inside "com.example.pkg1"
        def m = "m11"

      class Class12:              // Class inside "com.example.pkg1"
        def m = "m12"

    package pkg2:                 // Subpackage of "example"
      class Class21:              // Class inside "com.example.pkg2"
        def m = "m21"
        def makeClass11 = pkg1.Class11()

        def makeClass12 = pkg1.Class12()

    package pkg3.pkg31.pkg311:    // More concise nesting of packages
      class Class311:
        def m = "m21"

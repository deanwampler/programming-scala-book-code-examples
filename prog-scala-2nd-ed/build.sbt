name := "Programming Scala, Second Edition: Code examples"

version := "2.0-M001"

organization := "org.programming-scala"

// Build against several versions of Scala
crossScalaVersions := Seq("2.10.3", "2.11.0-M7")

libraryDependencies ++= Seq(
  "org.scalacheck" %% "scalacheck" % "1.11.1"      % "test",
  "org.scalatest"  %% "scalatest"  % "2.0.1-SNAP4" % "test"
//  "org.scalacheck" % "scalacheck_2.11.0-M7" % "1.11.1"      % "test",
//  "org.scalatest"  % "scalatest_2.11.0-M7"  % "2.0.1-SNAP4" % "test",
)

// append -deprecation to the options passed to the Scala compiler
scalacOptions ++= Seq("-deprecation")

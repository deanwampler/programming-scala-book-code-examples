name := "Programming Scala, Second Edition: Code examples"

version := "2.0-M001"

organization := "org.programming-scala"

// Build against several versions of Scala
crossScalaVersions := Seq("2.10.3", "2.11.0-M7")

resolvers ++= Seq(
  "Sonatype Releases" at "http://oss.sonatype.org/content/repositories/releases"
)

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor" % "2.2.2",
  "org.scalacheck"    %% "scalacheck" % "1.11.1"      % "test",
  "org.scalatest"     %% "scalatest"  % "2.0.1-SNAP4" % "test",
  // JUnit is used for some Jav interop. examples:
  "junit"              % "junit"      % "4.11"        % "test"
)

// append -deprecation to the options passed to the Scala compiler
scalacOptions ++= Seq("-encoding", "UTF-8", "-optimise", "-deprecation", "-unchecked")

javacOptions  ++= Seq("-Xlint:unchecked", "-Xlint:deprecation")

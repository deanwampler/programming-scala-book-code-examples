name := "Programming Scala, Second Edition: Code examples"

version := "2.0-M002"

organization := "org.programming-scala"

// Build against several versions of Scala
// BEGIN CROSS_VERSIONS
crossScalaVersions := Seq("2.10.3", "2.11.0-M5")
// END CROSS_VERSIONS

// It's tricky findng versions of the dependencies for the 2.11 milestone releases!
// Also, Scala XML is a separate library in 2.11. 
libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor" % "2.2.1",
  "org.scalacheck"    %% "scalacheck" % "1.10.1" % "test",
  "org.scalatest"     %% "scalatest"  % "2.0.M7" % "test" cross 
    CrossVersion.fullMapped {
      case "2.11.0-M5" => "2.11.0-M5"
      case "2.10.3"    => "2.10" 
    },
  // JUnit is used for some Java interop. examples. A driver for JUnit:
  "junit"        % "junit-dep"       % "4.10" % "test",
  "com.novocode" % "junit-interface" % "0.10" % "test"
) ++ (
  if (scalaVersion.value startsWith "2.11") 
    Seq(
      "org.scala-lang.modules" %% "scala-parser-combinators" % "1.0-RC2",
      "org.scala-lang.modules" %% "scala-xml" % "1.0.0-RC5")
  else Seq.empty)

// Options passed to the Scala and Java compilers:
scalacOptions ++= Seq(
"-encoding", "UTF-8", "-optimise", "-deprecation", "-unchecked", "-feature")

javacOptions  ++= Seq("-Xlint:unchecked", "-Xlint:deprecation")

name := "Programming Scala, Second Edition - Code examples"

version := "2.1"

organization := "org.programming-scala"

scalaVersion := "2.11.7"

libraryDependencies ++= Seq(
  "org.scala-lang.modules" %% "scala-async"     % "0.9.2",
  "org.scala-lang.modules" %% "scala-parser-combinators" % "1.0.2",
  "org.scala-lang.modules" %% "scala-xml"       % "1.0.2",
  "org.scala-lang"          % "scala-reflect"   % scalaVersion.value,
  "com.typesafe.akka"      %% "akka-actor"      % "2.3.4",
  "com.typesafe.akka"      %% "akka-slf4j"      % "2.3.4",
  "ch.qos.logback"          % "logback-classic" % "1.1.2",
  "org.scalaz"             %% "scalaz-core"     % "7.1.0",
  "org.scalacheck"         %% "scalacheck"      % "1.12.5" % "test",
  "org.scalatest"          %% "scalatest"       % "2.2.4"  % "test",
  "org.specs2"             %% "specs2"          % "2.4"    % "test",
  // JUnit is used for some Java interop. examples. A driver for JUnit:
  "junit"                   % "junit-dep"       % "4.10"   % "test",
  "com.novocode"            % "junit-interface" % "0.10"   % "test"
)

scalacOptions ++= Seq(
  "-encoding", "UTF-8", "-optimise",
  "-deprecation", "-unchecked", "-feature", "-Xlint",
  "-Ywarn-infer-any", // Nice, but hard to eliminate these warnings: "-Ywarn-value-discard")
  "-language:experimental.macros")

javacOptions  ++= Seq(
  "-Xlint:unchecked", "-Xlint:deprecation") // Java 8: "-Xdiags:verbose")

// Enable improved incremental compilation feature in 2.11.X.
// see http://www.scala-lang.org/news/2.11.1
incOptions := incOptions.value.withNameHashing(true)

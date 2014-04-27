name := "Programming Scala, Second Edition: Code examples"

version := "2.0-M002"

organization := "org.programming-scala"

// Build against several versions of Scala
crossScalaVersions := Seq("2.10.4", "2.11.0")

// It's tricky findng versions of the deps. for the 2.11 milestone releases!
// Also, Scala XML is a separate library in 2.11. 
libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor" % "2.3.2",
  "org.scalacheck"    %% "scalacheck" % "1.11.3" % "test",
  "org.scalatest"     %% "scalatest"  % "2.1.4"  % "test",
  // JUnit is used for some Java interop. examples. A driver for JUnit:
  "junit"        % "junit-dep"       % "4.10" % "test",
  "com.novocode" % "junit-interface" % "0.10" % "test"
) ++ (
  if (scalaVersion.value startsWith "2.11") 
    Seq(
      "org.scala-lang.modules" %% "scala-parser-combinators" % "1.0.0",
      "org.scala-lang.modules" %% "scala-xml" % "1.0.0")
  else Seq.empty)

// enable improved (experimental) incremental compilation algorithm called 
// "name hashing"
incOptions := incOptions.value.withNameHashing(true)

val commonOptions = Seq(
  "-encoding", "UTF-8", "-optimise", 
  "-deprecation", "-unchecked", "-feature")
// "-Yinline-warnings" - Warns if constructs have the @inline annotation, but
// inlining isn't possible. More annoying than useful, most of the time...

// Options passed to the Scala and Java compilers:
scalacOptions <<= scalaVersion map { version: String => 
  if (version.startsWith("2.10")) commonOptions
  else commonOptions ++ Seq("-Ywarn-infer-any") // Warn if "Any" is inferred
}

javacOptions  ++= Seq("-Xlint:unchecked", "-Xlint:deprecation")

// Options passed to the Scala REPL:
//consoleOptions ++= commonOptions

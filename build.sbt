name := "Programming Scala, Third Edition - Code examples"

version := "3.0.0-140"

organization := "org.programming-scala"

scalaVersion := "0.27.0-RC1"

maxErrors := 10

libraryDependencies ++= {
  lazy val versions =
    CrossVersion.partialVersion(scalaVersion.value) match {
      case Some((0, n))  => Map("async" -> "0.10.0", "akka" -> "2.6.1")
      case Some((m, n))  => println(s"Unrecognized compiler version $m.$n"); sys.exit(1)
      case None          => println("CrossVersion.partialVersion(scalaVersion.value) returned None!!"); sys.exit(1)
    }
  Seq(
    "org.scala-lang.modules" %% "scala-parser-combinators" % "1.1.2",
    "com.typesafe.akka"      %% "akka-actor"       % "2.6.8",
    "com.typesafe.akka"      %% "akka-slf4j"       % "2.6.8",
    "ch.qos.logback"          % "logback-classic"  % "1.2.3",
    "org.typelevel"          %% "cats-core"        % "2.1.1",
    "org.scalacheck"         %% "scalacheck"       % "1.14.1" % Test,
  ).map(dep => dep.withDottyCompat(scalaVersion.value)) ++ Seq(
    // Libraries that already fully support Dotty
    "org.scalameta"          %% "munit"            % "0.7.12" % Test,
    "org.scalameta"          %% "munit-scalacheck" % "0.7.12" % Test
  )
}

testFrameworks += new TestFramework("munit.Framework")

// For Scala 3 (Dotty)
// The -rewrite and -migration options are best used while migrating
// from Scala 2 to Scala 3, then removed.
scalacOptions in Compile := Seq(
  "-deprecation",                      // Emit warning and location for usages of deprecated APIs.
  "-encoding", "utf-8",                // Specify character encoding used by source files.
  "-unchecked",                        // Enable additional warnings where generated code depends on assumptions.
  "-feature",                          // Emit warning and location for usages of features that should be imported explicitly.
  // "-language:strictEquality",          // Require +derives Eql+ for using == or != comparisons
  "-explain",                          // Explain errors in more detail.
  "-explain-types",                    // Explain type errors in more detail.
  // "-noindent",                         // Require classical {...} syntax, indentation is not significant.
  // "-old-syntax",                       // Require `(...)` around conditions.
  "-new-syntax",                       // Require `then` and `do` in control expressions.
  // "-language:Scala2",                  // Compile Scala 2 code, highlight what needs updating
  // "-migration",                        // Emit warning and location for migration issues from Scala 2.
  // "-rewrite",                          // Attempt to fix code automatically
  // "-Ycheck-init",                      // Warn on field access before initialization
  "-Xfatal-warnings",                  // Fail on warnings, not just errors
  // "-Yexplicit-nulls"                   // For explicit nulls behavior.
)
scalacOptions in (Compile, console) := scalacOptions.value

javacOptions  ++= Seq(
  "-Xlint:unchecked", "-Xlint:deprecation") // Java 8: "-Xdiags:verbose")

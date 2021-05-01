lazy val root = project
  .in(file("."))
  .settings(
    name := "programming-scala-3rd-ed-code-examples",
    description := "Code examples for Programming Scala, Third Edition (O'Reilly).",
    version := "3.0.0-RC3-001",
    scalaVersion := "3.0.0-RC3",
    organization := "org.programming-scala",
    organizationName := "ProgrammingScala",
    organizationHomepage := Some(url("http://programming-scala.org")),
    homepage := Some(url("https://github.com/deanwampler/programming-scala-book-code-examples/")),
    licenses += "Apache2" -> url("http://www.apache.org/licenses/LICENSE-2.0"),
    maxErrors := 10,
    libraryDependencies ++= Seq(
      "com.typesafe.akka"      %% "akka-actor-typed" % "2.6.14",
      "com.typesafe.akka"      %% "akka-slf4j"       % "2.6.14",
      // Map over this sequence of Scala 2.X libraries & call withDottyCompat(...):
    ).map(dep => dep.withDottyCompat(scalaVersion.value)) ++ Seq(
      // Libraries that already fully support Dotty/Scala 3:
      "org.typelevel"          %% "cats-core"        % "2.6.0",
      "org.scala-lang"         %% "scala3-staging"   % scalaVersion.value,
      "org.scala-lang.modules" %% "scala-parser-combinators" % "1.2.0-RC2",
      "ch.qos.logback"          % "logback-classic"  % "1.2.3",
      "org.scalacheck"         %% "scalacheck"       % "1.15.3" % Test,
      "org.scalameta"          %% "munit"            % "0.7.25" % Test,
      "org.scalameta"          %% "munit-scalacheck" % "0.7.25" % Test
    ),

    // For Scala 3 (Dotty)
    // The -rewrite and -migration options are best used while migrating
    // from Scala 2 to Scala 3, then removed.
    // The default value for -source is 3.0. I'm using 3.1 to force more
    // deprecation warnings for obsolete concepts that are being transitioned
    // out. Use the default value if you are migrating from Scala 2!!
    scalacOptions := Seq(
      // "-classpath", "foo:bar:...",         // Add to the classpath.
      "-encoding", "utf-8",                // Specify character encoding used by source files.
      "-deprecation",                      // Emit warning and location for usages of deprecated APIs.
      "-unchecked",                        // Enable additional warnings where generated code depends on assumptions.
      "-feature",                          // Emit warning and location for usages of features that should be imported explicitly.
      "-explain",                          // Explain errors in more detail.
      // "-explain-types",                    // Explain type errors in more detail.
      // "-indent",                           // Together with -rewrite, remove {...} syntax when possible due to significant indentation.
      // "-no-indent",                        // Require classical {...} syntax, indentation is not significant.
      "-new-syntax",                       // Require `then` and `do` in control expressions.
      // "-old-syntax",                       // Require `(...)` around conditions.
      // "-language:Scala2",                  // Compile Scala 2 code, highlight what needs updating
      // "-language:strictEquality",          // Require +derives Eql+ for using == or != comparisons
      // "-rewrite",                          // Attempt to fix code automatically. Use with -indent and ...-migration.
      // "-scalajs",                          // Compile in Scala.js mode (requires scalajs-library.jar on the classpath).
      "-source:future",                       // Choices: future and future-migration. I use this to force future deprecation warnings, etc.
      "-Xfatal-warnings",                  // Fail on warnings, not just errors
      // "-Xmigration",                       // Warn about constructs whose behavior may have changed since version.
      // "-Ysafe-init",                       // Warn on field access before initialization
      // "-Yexplicit-nulls",                  // For explicit nulls behavior.
    ),
    Compile / console / scalacOptions := scalacOptions.value,
    javacOptions ++= Seq(
      "-Xlint:unchecked", "-Xlint:deprecation") // Java 8: "-Xdiags:verbose"),
  )

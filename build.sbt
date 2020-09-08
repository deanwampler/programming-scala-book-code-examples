lazy val root = project.in(file(".")).
  aggregate(book.js, book.jvm).
  settings(
    publish := {},
    publishLocal := {},
  )

lazy val book = crossProject(JSPlatform, JVMPlatform).in(file(".")).
  settings(
    name := "Programming Scala, Third Edition - Code examples",
    version := "3.0.0-140",
    organization := "org.programming-scala",
    scalaVersion := "0.27.0-RC1",
    maxErrors := 10,
    scalaJSUseMainModuleInitializer := true,

    // For Scala 3 (Dotty)
    // The -rewrite and -migration options are best used while migrating
    // from Scala 2 to Scala 3, then removed.
    scalacOptions in Compile := Seq(
      "-deprecation",                      // Emit warning and location for usages of deprecated APIs.
      "-encoding", "utf-8",                // Specify character encoding used by source files.
      "-unchecked",                        // Enable additional warnings where generated code depends on assumptions.
      "-feature",                          // Emit warning and location for usages of features that should be imported explicitly.
      "-language:strictEquality",
      "-explain",                          // Explain errors in more detail.
      "-explain-types",                    // Explain type errors in more detail.
      // "-noindent",                         // Require classical {...} syntax, indentation is not significant.
      // "-old-syntax",                       // Require `(...)` around conditions.
      "-new-syntax",                       // Require `then` and `do` in control expressions.
      // "-language:Scala2",                  // Compile Scala 2 code, highlight what needs updating
      // "-migration",                        // Emit warning and location for migration issues from Scala 2.
      // "-rewrite",                          // Attempt to fix code automatically
      "-Xfatal-warnings",                  // Fail on warnings, not just errors
      // "-Yexplicit-nulls"                   // For explicit nulls behavior.
    ),
    scalacOptions in (Compile, console) := scalacOptions.value,
  ).
  jvmSettings(
    libraryDependencies ++= Seq(
        "org.scala-lang.modules" %% "scala-parser-combinators" % "1.1.2",
        "org.typelevel"          %% "cats-core"        % "2.2.0",
        "com.typesafe.akka"      %% "akka-actor"       % "2.6.8",
        "com.typesafe.akka"      %% "akka-slf4j"       % "2.6.8",
        "ch.qos.logback"          % "logback-classic"  % "1.2.3",
        "org.scala-js"           %% "scalajs-stubs"    % "1.0.0"  % "provided",
        "org.scalacheck"         %% "scalacheck"       % "1.14.1" % Test,
    ).map(dep => dep.withDottyCompat(scalaVersion.value)) ++ Seq(
      // Libraries that already fully support Dotty
      "org.scalameta"          %%% "munit"            % "0.7.12" % Test,
      "org.scalameta"          %%% "munit-scalacheck" % "0.7.12" % Test
    ),
    javacOptions  ++= Seq(
      "-Xlint:unchecked", "-Xlint:deprecation"), // Java 8: "-Xdiags:verbose")
    testFrameworks += new TestFramework("munit.Framework"),
  ).
  jsSettings(
    scalaJSUseMainModuleInitializer := true,
  )

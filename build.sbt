name := "Programming Scala, Third Edition - Code examples"

version := "3.0.0-001"

organization := "org.programming-scala"

val scala212Version = "2.12.10"
val scala213Version = "2.13.1"
val scala300Version = "0.22.0-RC1"
scalaVersion := scala300Version

crossScalaVersions := Seq(scala212Version, scala213Version, scala300Version)
maxErrors := 10

libraryDependencies ++= {
  lazy val versions =
    CrossVersion.partialVersion(scalaVersion.value) match {
      case Some((2, 12)) => Map("async" -> "0.10.0", "akka" -> "2.5.25")
      case Some((2, 13)) => Map("async" -> "0.10.0", "akka" -> "2.6.1")
      case Some((0, n))  => Map("async" -> "0.10.0", "akka" -> "2.6.1")
      case Some((m, n))  => println(s"Unrecognized compiler version $m.$n"); sys.exit(1)
      case None          => println("CrossVersion.partialVersion(scalaVersion.value) returned None!!"); sys.exit(1)
    }
  Seq(
    "org.scala-lang.modules" %% "scala-async"     % versions("async"),
    "org.scala-lang.modules" %% "scala-parser-combinators" % "1.1.2",
    "org.scala-lang.modules" %% "scala-xml"       % "1.2.0",
    // "org.scala-lang"          % "scala-reflect"   % scalaVersion.value,
    "com.typesafe.akka"      %% "akka-actor"      % versions("akka"),
    "com.typesafe.akka"      %% "akka-slf4j"      % versions("akka"),
    "ch.qos.logback"          % "logback-classic" % "1.2.3",
    "org.typelevel"          %% "cats-core"       % "2.0.0",
    "org.scalacheck"         %% "scalacheck"      % "1.14.1" % "test",
    "org.scalatest"          %% "scalatest"       % "3.1.0"  % "test",
  ).map(dep => dep.withDottyCompat(scalaVersion.value))
}

// The compiler flags adapted from @tpolecat's Doobie project. https://github.com/tpolecat/doobie

lazy val scalacOptionsAllCommon = Seq(
  "-deprecation",                      // Emit warning and location for usages of deprecated APIs.
  "-encoding", "utf-8",                // Specify character encoding used by source files.
  "-unchecked",                        // Enable additional warnings where generated code depends on assumptions.
  "-feature",                          // Emit warning and location for usages of features that should be imported explicitly.
  "-explaintypes",                     // Explain type errors in more detail.
  "-language:existentials",            // Existential types (besides wildcard types) can be written and inferred
  "-language:higherKinds",             // Allow higher-kinded types
  "-language:implicitConversions"      // Allow definition of implicit functions called views
)

lazy val scalacOptionsAll = scalacOptionsAllCommon ++ Seq(
  "-Ywarn-dead-code",                  // Warn when dead code is identified.
  "-Ywarn-numeric-widen",              // Warn when numerics are widened.
  "-Ywarn-value-discard",              // Warn when non-Unit expression results are unused.
  "-Xcheckinit",                       // Wrap field accessors to throw an exception on uninitialized access.
  "-Xfatal-warnings",                  // Fail the compilation if there are any warnings
  "-Xlint:adapted-args",               // Warn if an argument list is modified to match the receiver.
  "-Xlint:delayedinit-select",         // Selecting member of DelayedInit.
  "-Xlint:doc-detached",               // A Scaladoc comment appears to be detached from its element.
  "-Xlint:inaccessible",               // Warn about inaccessible types in method signatures.
  "-Xlint:infer-any",                  // Warn when a type argument is inferred to be `Any`.
  "-Xlint:missing-interpolator",       // A string literal appears to be missing an interpolator id.
  "-Xlint:nullary-override",           // Warn when non-nullary `def f()' overrides nullary `def f'.
  "-Xlint:nullary-unit",               // Warn when nullary methods return Unit.
  "-Xlint:option-implicit",            // Option.apply used implicit view.
  "-Xlint:package-object-classes",     // Class or object defined in package object.
  "-Xlint:poly-implicit-overload",     // Parameterized overloaded implicit methods are not visible as view bounds.
  "-Xlint:private-shadow",             // A private field (or class parameter) shadows a superclass field.
  "-Xlint:stars-align",                // Pattern sequence wildcard must align with sequence component.
  "-Xlint:type-parameter-shadow"       // A local type parameter shadows a type already in scope.
)

lazy val scalacOptionsAllCompile = scalacOptionsAll ++ Seq(
  // "-Yno-imports"                    // Compile without importing scala.*, java.lang.*, or Predef
)

lazy val scalacOptionsAllConsole = scalacOptionsAllCommon ++ Seq(
  "-Ydelambdafy:inline"                // Strategy used for translating lambdas into JVM code. (inline,method) default:inline
)

lazy val scalacOptions11Console = Seq(
  "-language:experimental.macros",
  "-Xfuture",                          // Turn on future language features.
  "-Xlint:by-name-right-associative",  // By-name parameter of right associative operator.
  "-Xlint:unsound-match",              // Pattern match may not be typesafe.
  "-Ypartial-unification",             // Enable partial unification in type constructor inference
  "-Ywarn-infer-any"                   // Warn if Any is inferred for a type
) ++ scalacOptionsAllConsole

lazy val scalacOptions11Compile = Seq(
  "-language:experimental.macros",
  "-Xfuture",                          // Turn on future language features.
  "-Xlint:by-name-right-associative",  // By-name parameter of right associative operator.
  "-Xlint:unsound-match",              // Pattern match may not be typesafe.
  "-Ypartial-unification",             // Enable partial unification in type constructor inference
  "-Ywarn-unused-import",              // Warn when imports are unused
  "-Ywarn-infer-any",                  // Warn if Any is inferred for a type
  "-optimise"
) ++ scalacOptionsAllCompile

// Common for 2.12 and 2.13:
lazy val scalacOptions1213Console = Seq(
  "-Xlint:constant",                   // Evaluation of a constant arithmetic expression results in an error.
  "-Ywarn-extra-implicit"              // Warn when more than one implicit parameter section is defined.
) ++ scalacOptionsAllConsole

// Common for 2.12 and 2.13:
lazy val scalacOptions1213Compile = Seq(
  "-Xlint:constant",                   // Evaluation of a constant arithmetic expression results in an error.
  "-Ywarn-extra-implicit",             // Warn when more than one implicit parameter section is defined.
  "-Ywarn-unused:implicits",           // Warn if an implicit parameter is unused.
  "-Ywarn-unused:imports",             // Warn if an import selector is not referenced.
  "-Ywarn-unused:locals",              // Warn if a local definition is unused.
  "-Ywarn-unused:params",              // Warn if a value parameter is unused.
  // Recommended, but turned off because it causes problems with the macro example, metaprogramming.invariant
  // "-Ywarn-unused:patvars",             // Warn if a variable bound in a pattern is unused.
  "-Ywarn-unused:privates"             // Warn if a private member is unused.
) ++ scalacOptionsAllCompile

// Removed in Scala 2.13:
lazy val scalacOptions12Console = Seq(
  "-Xfuture",                          // Turn on future language features.
  "-Xlint:by-name-right-associative",  // By-name parameter of right associative operator.
  "-Xlint:unsound-match",              // Pattern match may not be typesafe.
  "-Ywarn-infer-any",                  // Warn if Any is inferred for a type
  "-Yno-adapted-args",                 // Do not adapt an argument list (either by inserting () or creating a tuple) to match the receiver.
  "-Ypartial-unification"              // Enable partial unification in type constructor inference
) ++ scalacOptions1213Console

// Removed in Scala 2.13:
lazy val scalacOptions12Compile = Seq(
  "-Xfuture",                          // Turn on future language features.
  "-Xlint:by-name-right-associative",  // By-name parameter of right associative operator.
  "-Xlint:unsound-match",              // Pattern match may not be typesafe.
  "-Ywarn-infer-any",                  // Warn if Any is inferred for a type
  "-Yno-adapted-args",                 // Do not adapt an argument list (either by inserting () or creating a tuple) to match the receiver.
  "-Ypartial-unification"              // Enable partial unification in type constructor inference
) ++ scalacOptions1213Compile

// For Scala 3 (Dotty)
lazy val scalacOptions3Console = Seq(
  "-deprecation",                      // Emit warning and location for usages of deprecated APIs.
  "-encoding", "utf-8",                // Specify character encoding used by source files.
  "-unchecked",                        // Enable additional warnings where generated code depends on assumptions.
  "-feature",                          // Emit warning and location for usages of features that should be imported explicitly.
  "-explain",                          // Explain errors in more detail.
  "-explain-types",                    // Explain type errors in more detail.
  "-noindent",                         // Require classical {...} syntax, indentation is not significant.
  "-old-syntax",                       // Require `(...)` around conditions.
  // "-new-syntax",                       // Require `then` and `do` in control expressions.
  "-strict",                           // Use strict type rules, which means some formerly legal code does not typecheck anymore.
  "-migration",                        // Emit warning and location for migration issues from Scala 2.
  "-language:Scala2"                   // Compile Scala 2 code, highlight what needs updating
//  "-rewrite"                            // Attempt to fix code automatically
)

lazy val scalacOptions3Compile = scalacOptions3Console

scalacOptions in Compile := (
  CrossVersion.partialVersion(scalaVersion.value) match {
    case Some((2, 11)) => scalacOptions11Compile
    case Some((2, 12)) => scalacOptions12Compile
    case Some((2, 13)) => scalacOptions1213Compile
    case Some((0, n))  => scalacOptions3Compile
    case Some((m, n))  => println(s"Unrecognized compiler version $m.$n"); sys.exit(1)
    case None          => println("CrossVersion.partialVersion(scalaVersion.value) returned None!!"); sys.exit(1)
  }
)

scalacOptions in (Compile, console) := (
  CrossVersion.partialVersion(scalaVersion.value) match {
    case Some((2, 11)) => scalacOptions11Console
    case Some((2, 12)) => scalacOptions12Console
    case Some((2, 13)) => scalacOptions1213Console
    case Some((0, n))  => scalacOptions3Console
    case Some((m, n))  => println(s"Unrecognized compiler version $m.$n"); sys.exit(1)
    case None          => println("CrossVersion.partialVersion(scalaVersion.value) returned None!!"); sys.exit(1)
  }
)

javacOptions  ++= Seq(
  "-Xlint:unchecked", "-Xlint:deprecation") // Java 8: "-Xdiags:verbose")

name := "Programming Scala, Second Edition - Code examples"

version := "2.2"

organization := "org.programming-scala"

scalaVersion := "2.12.8"
crossScalaVersions := Seq("2.11.12", "2.12.8", "2.13.0-RC2")

libraryDependencies ++= {
  lazy val versions =
    CrossVersion.partialVersion(scalaVersion.value) match {
      case Some((2, 11)) => Map("async" -> "0.9.7",  "akka" -> "2.5.22")
      case Some((2, 12)) => Map("async" -> "0.10.0", "akka" -> "2.5.22")
      case Some((2, 13)) => Map("async" -> "0.10.0", "akka" -> "2.6.0-M2")
      case Some((m, n))  => println(s"Unrecognized compiler version $m.$n"); sys.exit(1)
      case None          => println("CrossVersion.partialVersion(scalaVersion.value) returned None!!"); sys.exit(1)
    }
  Seq(
    "org.scala-lang.modules" %% "scala-async"     % versions("async"),
    "org.scala-lang.modules" %% "scala-parser-combinators" % "1.1.2",
    "org.scala-lang.modules" %% "scala-xml"       % "1.2.0",
    "org.scala-lang"          % "scala-reflect"   % scalaVersion.value,
    "com.typesafe.akka"      %% "akka-actor"      % versions("akka"),
    "com.typesafe.akka"      %% "akka-slf4j"      % versions("akka"),
    "ch.qos.logback"          % "logback-classic" % "1.2.3",
    "org.scalaz"             %% "scalaz-core"     % "7.2.27",
    "org.scalacheck"         %% "scalacheck"      % "1.14.0" % "test",
    "org.scalatest"          %% "scalatest"       % "3.0.8-RC4" % "test", // threading the needle on versions...
    "org.specs2"             %% "specs2-core"     % "4.5.1"  % "test",
    // JUnit is used for some Java interop. examples. A driver for JUnit:
    "junit"                   % "junit-dep"       % "4.11"   % "test",
    "com.novocode"            % "junit-interface" % "0.11"   % "test"
  )
}

// The compiler flags adapted from @tpolecat's Doobie project. https://github.com/tpolecat/doobie

lazy val scalacOptionsAll = Seq(
  "-deprecation",                      // Emit warning and location for usages of deprecated APIs.
  "-encoding", "utf-8",                // Specify character encoding used by source files.
  "-unchecked",                        // Enable additional warnings where generated code depends on assumptions.
  "-feature",                          // Emit warning and location for usages of features that should be imported explicitly.
  "-explaintypes",                     // Explain type errors in more detail.
  "-language:existentials",            // Existential types (besides wildcard types) can be written and inferred
  "-language:higherKinds",             // Allow higher-kinded types
  "-language:implicitConversions",     // Allow definition of implicit functions called views
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
lazy val scalacOptionsAllCompile = Seq(
  // "-Yno-imports"                    // Compile without importing scala.*, java.lang.*, or Predef
)

lazy val scalacOptionsAllConsole = Seq(
  "-Ydelambdafy:inline"                // Strategy used for translating lambdas into JVM code. (inline,method) default:inline
)

lazy val scalacOptions11 = Seq(
  "-language:experimental.macros",
  "-Xfuture",                          // Turn on future language features.
  "-Xlint:by-name-right-associative",  // By-name parameter of right associative operator.
  "-Xlint:unsound-match",              // Pattern match may not be typesafe.
  "-Ypartial-unification",             // Enable partial unification in type constructor inference
  "-Ywarn-unused-import",              // Warn when imports are unused
  "-Ywarn-infer-any",                  // Warn if Any is inferred for a type
  "-optimise"
) ++ scalacOptionsAll

// Common for 2.12 and 2.13:
lazy val scalacOptions1213 = Seq(
  "-Xlint:constant",                   // Evaluation of a constant arithmetic expression results in an error.
  "-Ywarn-extra-implicit",             // Warn when more than one implicit parameter section is defined.
  "-Ywarn-unused:implicits",           // Warn if an implicit parameter is unused.
  "-Ywarn-unused:imports",             // Warn if an import selector is not referenced.
  "-Ywarn-unused:locals",              // Warn if a local definition is unused.
  "-Ywarn-unused:params",              // Warn if a value parameter is unused.
  // Recommended, but turned off because it causes problems with the macro example, metaprogramming.invariant
  //"-Ywarn-unused:patvars",             // Warn if a variable bound in a pattern is unused.
  "-Ywarn-unused:privates",            // Warn if a private member is unused.
) ++ scalacOptionsAll

// Removed in Scala 2.13:
lazy val scalacOptions12 = scalacOptions1213 ++ Seq(
  "-Xfuture",                          // Turn on future language features.
  "-Xlint:by-name-right-associative",  // By-name parameter of right associative operator.
  "-Xlint:unsound-match",              // Pattern match may not be typesafe.
  "-Ywarn-infer-any",                  // Warn if Any is inferred for a type
  "-Yno-adapted-args",                 // Do not adapt an argument list (either by inserting () or creating a tuple) to match the receiver.
  "-Ypartial-unification"              // Enable partial unification in type constructor inference
)

scalacOptions ++= (
  CrossVersion.partialVersion(scalaVersion.value) match {
    case Some((2, 11)) => scalacOptions11 ++ scalacOptionsAllCompile
    case Some((2, 12)) => scalacOptions12 ++ scalacOptionsAllCompile
    case Some((2, 13)) => scalacOptions1213 ++ scalacOptionsAllCompile
    case Some((m, n))  => println(s"Unrecognized compiler version $m.$n"); sys.exit(1)
    case None          => println("CrossVersion.partialVersion(scalaVersion.value) returned None!!"); sys.exit(1)
  }
)

scalacOptions in (Compile, console) ++= (
  CrossVersion.partialVersion(scalaVersion.value) match {
    case Some((2, 11)) => scalacOptions11 ++ scalacOptionsAllConsole
    case Some((2, 12)) => scalacOptions12 ++ scalacOptionsAllConsole
    case Some((2, 13)) => scalacOptions1213 ++ scalacOptionsAllConsole
    case Some((m, n))  => println(s"Unrecognized compiler version $m.$n"); sys.exit(1)
    case None          => println("CrossVersion.partialVersion(scalaVersion.value) returned None!!"); sys.exit(1)
  }
)

javacOptions  ++= Seq(
  "-Xlint:unchecked", "-Xlint:deprecation") // Java 8: "-Xdiags:verbose")

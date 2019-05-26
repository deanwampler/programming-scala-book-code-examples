# Programming Scala, 2nd Edition

## README for the Code Examples

*Dean Wampler*<br/>
*August 11, 2014*<br/>
*May 27, 2019* - Updated for Scala 2.12 and 2.13

[![Join the chat at https://gitter.im/deanwampler/prog-scala-2nd-ed-code-examples](https://badges.gitter.im/deanwampler/prog-scala-2nd-ed-code-examples.svg)](https://gitter.im/deanwampler/prog-scala-2nd-ed-code-examples?utm_source=badge&utm_medium=badge&utm_campaign=pr-badge&utm_content=badge)

This archive contains all the code examples found in [Programming Scala, Second Edition](http://shop.oreilly.com/product/0636920033073.do), with the exception of some trivial code snippets in the text. There are also some examples in this distribution that aren't actually in the book.

When the book was published, the examples used Scala 2.11. The code was recently updated to also compile with Scala 2.12.8 and 2.13.0-RC2. The examples are mostly unchanged, except where necessary to compile with these newer versions and with the stricter compiler flags now used.

If you want the original code (with a few bug fixes), download the tagged `2.1.0` build or check out the `release-2.1.0` branch. The `2.3.0` release and `release-2.3.0` branch include all the updates for 2.12 and 2.13.

## How the Code Is Used in the Book

In the book's text, when an example corresponds to a file in this distribution, the listing begins with a path in a comment with the following format:

```
// src/main/scala/progscala2/chapter/../filename
```

And similarly for Java files (yes, there are Java files!). Following the usual conventions, tests are in `src/test/...`.

> **NOTE:** When I upgraded support for 2.12 and 2.13, a few of the `*.scala` files were converted to `*.sc` script files (see below), because they are no long compilable as is with the new, stricter compile flags I'm using.

Use these comments to find the corresponding source file. This archive also contains *ScalaTest* and *ScalaCheck* unit tests to validate some of the code. Most of these tests are not reproduced in the text of the book, except when discussing testing itself.

## Naming Conventions

The examples include "scripts" that are run with the `scala` command (or within SBT using the `console`), source files that are compiled with `scalac`, and source files that deliberately fail to compile to demonstrate common errors. To keep them straight and to support building with SBT, the following naming conventions are used for the files:

- `build.sbt` - The SBT build script (described below).
- `*.scala` - Source files that are compiled with `scala`. In fact, this is the community-standard extension for all Scala files, code to be compiled or scripts. But to keep the build process simple, I use different conventions for files that aren't compiled, discussed next.
- `*.sc` - Script files that are executed directly, e.g., `scala foo-script.sc`. This file extension is not a standard, but it is used by the newer IDE *worksheet* feature I discuss in the book. So, I stole the convention; SBT will ignore these scripts when compiling. These script don't have tests to verify them (TODO).
- `*.javaX`, `*.scalaX` and `*.scX` - Java and Scala source files and scripts with deliberate errors, so they don't compile and run, or building them would require significant changes to the build that were deemed unnecessary. Most contain comments explaining what's wrong with them or in some cases, the corresponding section of the book provides the details.

## Required and Optional Tools

To build and run the examples, all you need to do is install [SBT](http://www.scala-sbt.org/release/docs/Getting-Started/Setup.html). SBT is the de-facto standard build tool for Scala. When you run SBT, it will bootstrap itself with the correct version of its jar file, Scala, and project dependencies, which are specified in the `build.sbt` file in the root directory and other build files in the `project` directory.

Follow these [installation instructions](http://www.scala-sbt.org/release/docs/Getting-Started/Setup.html).

If you want to install Scala separately and *Scaladocs* (recommended), go to [scala-lang.org](http://scala-lang.org), but this isn't strictly required.

### Editors, IntelliJ, Visual Studio Code, and Other IDEs

Most editors and IDEs now have some sort of Scala support:

* [IntelliJ](https://www.jetbrains.com/idea/): Either the Community or Ultimate additions will work. Install the Scala plugin, which has built-in support for SBT.
* [Visual Studio Code](https://code.visualstudio.com/): Use the new [Scala Metals](https://scalameta.org/metals/) plugin instead of older plugins.
* [Eclipse Scala IDE](http://scala-ide.org): Old, no longer recommended.

For other IDEs and text editors, try [Scala Metals](https://scalameta.org/metals/) first (I've used it with [Sublime Text](https://www.sublimetext.com/), for example) or [ENSIME](http://ensime.github.io/). You may also need additional, third-party tools for syntax highlighting, etc.

After installing the required plugins, load this project in your IDE, which should detect and use the SBT project automatically. For eclipse, run the `sbt eclipse` task to generate project files, then import them.

## Building the Code Examples

After installing SBT, open a command/terminal window and run the `sbt test` command. By default, it now uses Scala 2.12.8. To build and test for Scala 2.11, 2.12, and 2.13, run `sbt +test`.

You'll see lots of output as it downloads all the dependencies, compiles the code and runs the tests. You should see `[success]` messages at the end.

SBT is discussed in more detail in the book and the [SBT website](https://www.scala-sbt.org/), but a few useful commands are worth mentioning here.

If you start `sbt` without any arguments, it puts you into an interactive mode where you can type commands. Use control-D to exit this mode. Once at the SBT prompt (a `>`), try the following commands, where each `#` starts a comment; don't type those!

	help       # help on tasks and settings
	clean      # delete all build outputs
	compile    # compile the source, but not test code
	test       # compile source and test code, if necessary and run the tests.
	+test      # compile and test for all versions of Scala supported.
	~test      # continuously compile and test when source changes are saved.
	tasks      # show the most common tasks (commands).
	tasks -V   # REALLY show ALL tasks

Note the `+test` example. It looks at a property named `crossScalaVersions` in the build file, `build.sbt` to know which versions of Scala to use. The `+` can be used for any task, although for some it will make little sense. Similarly, the `~` prefix causes the task to be run continuously each time source code changes are saved. This promotes continuous TDD (test-driven development) and is one of my favorite features!

Outside of SBT, you could, in principle, run the script files manually at the console/terminal prompt.

    scala src/main/scala/.../foo.sc

However, many of the scripts require other project code that has been compiled (which is in `target/scala-2.11/classes`) and occasionally third-party libraries that are part of the project dependencies.

For example, if build artifacts are required on the class path, use the following command from the *root* directory of the distribution:

    scala -classpath target/scala-2.11/classes src/main/scala/.../foo.sc

Usually, the best way to run the scripts is to start `sbt` and run `console` to start the Scala REPL with all the dependencies added to the classpath. Then, use the REPL `:load src/main/scala/.../foo.sc` to load and run the script.

## Feedback

I welcome feedback on the Book and these examples. Please post comments, corrections, etc. to one of the following places:

* This GitHub repo's [Gitter channel](https://gitter.im/deanwampler/prog-scala-2nd-ed-code-examples) or [Issues](https://github.com/deanwampler/prog-scala-2nd-ed-code-examples/issues)
* The book's Twitter account, [@ProgScala](https://twitter.com/ProgScala)
* The O'Reilly book site, [Programming Scala, Second Edition](http://shop.oreilly.com/product/0636920033073.do)
* The [O'Reilly errata page](http://oreilly.com/catalog/errata.csp?isbn=0636920033073)

There is also a dedicated site for the book where occasional updates, clarifications, corrections, and lame excuses will be posted: [programming-scala.org](http://programming-scala.org).

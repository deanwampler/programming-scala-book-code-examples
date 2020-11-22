# Programming Scala, 3rd Edition (Forthcoming)

## README for the Code Examples

[Dean Wampler](mailto:programming.scala@gmail.com)

| Key Dates         | Description |
| :---------------- | :---------- |
| August 11, 2014   | 2nd edition examples |
| May 27, 2019      | Updated for Scala 2.12 and 2.13 |
| June 18, 2019     | New support for Maven builds, courtesy of [oldbig](https://github.com/oldbig) |
| October 12, 2019  | Updated for Scala 2.13.1, sbt 1.3.2, and other dependencies. Also now compiles with JDK 11 |
| October 13, 2019  | Renamed the repo from `prog-scala-2nd-ed-code-examples` to `programming-scala-book-code-examples` |
| December 31, 2019 | Renamed the `progscala2` package to `progscala3` and reworked most of the `*.sc` scripts for better testability and other improvements |
| March 1, 2020     | Completed conversion to Scala 3 |
| March 20, 2020    | Started incorporating new Scala 3 syntax, idioms |
| November 11, 2020 | First Scala `3.0.0-M1` milestone changes |

[![Join the chat at https://gitter.im/deanwampler/programming-scala-book-code-examples](https://badges.gitter.im/deanwampler/programming-scala-book-code-examples.svg)](https://gitter.im/deanwampler/programming-scala-book-code-examples?utm_source=badge&utm_medium=badge&utm_campaign=pr-badge&utm_content=badge)

This repo contains all the code examples to be included in _Programming Scala, Third Edition_. (The second edition is [available here](http://shop.oreilly.com/product/0636920033073.do).) There are also many code files in this distribution that aren't included in the book.

When the second edition was published, the examples used Scala 2.11. The code has since been updated to also compile with Scala 2.12 and 2.13. Many examples are being improved, in part so they compile with newer library versions and with the stricter compiler flags now used. (ScalaTest, in particular, has changed a lot since the second edition.)

If you want the example code for the second edition (with a few bug fixes), [download the tagged 2.1.0](https://github.com/deanwampler/programming-scala-book-code-examples/releases/tag/2.1.0) build or check out the `release-2.1.0` branch. The latest `2.X.Y` release and `release-2.X.Y` branch include all the updates for 2.12 and 2.13. (No more `release-2.X.Y` releases are planned.) The third edition code for Scala 3 will be tagged with `release-3.0.0-XYZ`, with `XYZ` starting at `001` until the edition is published. Afterwards, `release-3.X.Y` will be used.

## How the Code Is Used in the Book

In the book's text, when an example corresponds to a file in this distribution, the listing begins with a path in a comment with the following format:

```
// src/main/scala/progscala3/.../filename
```

And similarly for Java files (yes, there are Java files!). Following the usual conventions, tests are in `src/test/...`.

Use these comments to find the corresponding source file. This archive also contains *MUnit* and *ScalaCheck* unit tests to validate some of the code. Most of these tests are not reproduced in the text of the book, except when discussing testing itself.

## Naming Conventions

The examples include "scripts" that are run with the `scala` command (or within SBT using the `console`), source files that are compiled with `scalac`, source files that deliberately fail to compile to demonstrate common errors, and other example files that aren't part of the build.

To keep these different kinds of files straight and to support building with SBT, the following conventions are used for the files:

- `src/main/scala/.../*.scala` - All Scala 3 source files built with SBT.
- `src/test/.../*.scala` - All Scala 3 test source files built and executed with SBT.
- `src/script/.../*.scala` - "Script" files that won't compile with `scalac`, but can be interpreted with the `scala` interpreter.
- `src/*/scala-2/.../*.scala` - All Scala 2 source files that won't compile with Scala 3. They are not built with SBT.

## Other Notes about the Code

You won't find many comments in the code, except of the form `// <1>`, which get converted into markers corresponding to notes in the book. All the descriptions of the code are in the book, so they aren't repeated as code comments.

Some files have sections marked like this:

```
// tag::section1[]
// end::section1[]
```

These are used to mark sections that are selectively included in the book. Sometimes the whole file is included in sections, while other times the file has content that isn't included ("Bonus!!").

## Required and Optional Tools

To build and run the examples, all you need Java 8 or newer and [SBT](http://www.scala-sbt.org/release/docs/Getting-Started/Setup.html). SBT is the de-facto standard build tool for Scala. When you run SBT, it will bootstrap itself with the correct version of its jar file, Scala, and project dependencies, which are specified in the `build.sbt` file in the root directory and other build files in the `project` directory.

Follow these [installation instructions](http://www.scala-sbt.org/release/docs/Getting-Started/Setup.html).

If you want to install Scala separately and Scala's *Scaladocs*, go to [scala-lang.org](http://scala-lang.org), but this isn't required.

If you want to play with the Spark example, `src/script/scala-2/progscala3/bigdata/SparkWordCount.scala`, you'll need to download a Spark distribution from https://spark.apache.org and use a compatible version of Scala, 2.12 at the time of this writing. With that version of scala on your path and assuming that `$SPARK_HOME` refers to the root directory of your Spark installations, run the following command in the root directory of this project:

```
$ $SPARK_HOME/bin/spark-shell
...
scala>
```

Then paste the content of `SparkWordCount.scala` at the prompt. After it runs, there will be a new directory, `README.md.wordcount` with the _partition_ files of the output.

> **Tip:** For more on Spark, see my free tutorial on GitHub, [spark-scala-tutorial](https://github.com/deanwampler/spark-scala-tutorial).

### Editors, IntelliJ, Visual Studio Code, and Other IDEs

Most editors and IDEs now have some sort of Scala support:

* [IntelliJ](https://www.jetbrains.com/idea/): Either the Community or Ultimate additions will work. Install the Scala plugin, which has built-in support for SBT.
* [Visual Studio Code](https://code.visualstudio.com/): Use the new [Scala Metals](https://scalameta.org/metals/) plugin instead of older plugins.
* [Eclipse Scala IDE](http://scala-ide.org): Old, no longer recommended.

For other IDEs and text editors, try [Scala Metals](https://scalameta.org/metals/) first (I've used it with [Sublime Text](https://www.sublimetext.com/), for example) or [ENSIME](http://ensime.github.io/). You may also need additional, third-party tools for syntax highlighting, etc.

After installing the required plugins, load this project in your IDE, which should detect and use the SBT project automatically. For eclipse, run the `sbt eclipse` task to generate project files, then import them.

## Building the Code Examples

After installing SBT, open a command/terminal window and run the `sbt test` command. By default, it now uses the _dotc_ preview for Scala 3.

You'll see lots of output as it downloads all the dependencies, compiles the code and runs the tests. You should see `[success]` messages at the end.

SBT is discussed in more detail in the book and the [SBT website](https://www.scala-sbt.org/), but a few useful commands are worth mentioning here.

If you start `sbt` without any arguments, it puts you into an interactive mode where you can type commands. Use control-D to exit this mode. Once at the SBT prompt (`sbt:Programming Scala, Third Edition - Code examples>`), try the following commands, where each `#` starts a comment; don't type those!

	help       # help on tasks and settings
	clean      # delete all build outputs
	compile    # compile the source, but not test code
	test       # compile source and test code, if necessary and run the tests.
	~test      # continuously compile and test when source changes are saved.
	console    # run the Scala REPL; dependencies and code are on the CLASSPATH
	tasks      # show the most common tasks (commands).
	tasks -V   # REALLY show ALL tasks

The `~` prefix causes the task to be run continuously each time source code changes are saved. This promotes continuous TDD (test-driven development) and is one of my favorite features!

Outside of SBT, you could, in principle, run the script files manually at the console/terminal prompt.

    scala src/script/scala/.../Foo.scala

However, many of the scripts require other project code that has been compiled (which is in `target/scala-X.Y/classes`) and occasionally third-party libraries that are part of the project dependencies. Hence, it's easier to test the scripts using the SBT `console` task. Use the REPL's `:load src/script/scala/.../Foo.scala` feature to load and run the script.

## Feedback

I welcome feedback on the Book and these examples. Please post comments, corrections, etc. to one of the following places:

* This GitHub repo's [Gitter channel](https://gitter.im/deanwampler/programming-scala-book-code-examples) or [Issues](https://github.com/deanwampler/programming-scala-book-code-examples/issues)
* The book's Twitter account, [@ProgScala](https://twitter.com/ProgScala)
* The O'Reilly book and errata sites, after publication.

There is also a dedicated site for the book where occasional updates, clarifications, corrections, and lame excuses will be posted: [programming-scala.org](http://programming-scala.org).

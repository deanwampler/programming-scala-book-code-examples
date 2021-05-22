# Programming Scala, 3rd Edition

## README for the Code Examples

* [Dean Wampler](mailto:programming.scala@gmail.com)
* [@deanwampler](https://twitter.com/deanwampler)
* [LinkedIn](https://www.linkedin.com/in/deanwampler/)
* [Book Page](http://programming-scala.org)
* [Blog about Scala 3](https://medium.com/scala-3)

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
| November 25, 2020 | First Scala `3.0.0-M2` milestone changes |
| December 19, 2020 | First Scala `3.0.0-M3` milestone changes |
| February 21, 2021 | Scala `3.0.0-RC1`  updates |
| April 3, 2021     | Scala `3.0.0-RC2`  updates |
| April 24, 2021    | Scala `3.0.0-RC3`  updates |
| May 15, 2021      | Scala `3.0.0` final  updates. Almost done! |
| May 22, 2021      | _Final_ updates for _Programming Scala, Third Edition_! |

[![Join the chat at https://gitter.im/deanwampler/programming-scala-book-code-examples](https://badges.gitter.im/deanwampler/programming-scala-book-code-examples.svg)](https://gitter.im/deanwampler/programming-scala-book-code-examples?utm_source=badge&utm_medium=badge&utm_campaign=pr-badge&utm_content=badge)

This repo contains all the code examples in [Programming Scala, Third Edition](http://programming-scala.org). (The second edition is [available here](http://shop.oreilly.com/product/0636920033073.do).) There are also many code files in this distribution that aren't included in the book.

The `master` branch and the `3.X.Y` tag releases are for the third edition. The code examples for the second edition are still available. [Download the release tagged 2.1.0](https://github.com/deanwampler/programming-scala-book-code-examples/releases/tag/2.1.0) or check out the `release-2.1.0` branch. While the second edition was published for 2.11. The latest `2.1.0` release and `release-2.1.0` are updated for 2.12 and 2.13. (No more `release-2.X.Y` releases are planned.)

## How the Code Is Used in the Book

In the book's text, when an example corresponds to a file in this distribution, the listing begins with a path in a comment with the following format:

```
// src/main/scala/progscala3/.../FooBar.scala
```

Following the usual conventions, tests are in `src/test/...`.

Use these comments to find the corresponding source file. This archive also contains *MUnit* and *ScalaCheck* unit tests to validate some of the code. 

## Naming Conventions

The examples include "scripts" that are intended for interactive use in the `scala` command-line "REPL" (read, eval, print loop), for example using `sbt console` (where [`sbt`](http://www.scala-sbt.org/release/docs/Getting-Started/Setup.html) is the de facto build tool for Scala that I use). Other files are compiled.

To keep these different kinds of files straight and to support building with `sbt`, the following conventions are used for the files:

- `src/main/scala/.../*.scala` - All Scala 3 source files built with `sbt`.
- `src/test/.../*.scala` - All Scala 3 test source files built and executed with `sbt`.
- `src/script/.../*.scala` - "Script" files that won't compile with `scalac`, but can be interpreted with the `scala` REPL or used as a worksheet (see below).
- `src/*/scala-2/.../*.scala` - Some Scala 2 source files that won't compile with Scala 3. They are not built with `sbt`.

## Other Notes about the Code

You won't find many comments in the code, except of the form `// <1>`, which get converted into markers corresponding to notes in the book. All the descriptions of the code are in the book, so they aren't repeated as code comments.

Some files have sections marked like this:

```
// tag::section1[]
// end::section1[]
```

These are used to mark sections that are selectively included in the book. Sometimes the whole file is included in sections, while other times the file has extra bonus content that doesn't appear in the book.

## Required and Optional Tools

To build and run the examples, all you need is a recent version of the the JDK and [`sbt`](http://www.scala-sbt.org/release/docs/Getting-Started/Setup.html). When you run `sbt`, it will bootstrap itself with the correct version of its jar file, Scala, and project dependencies, which are specified in the `build.sbt` file in the root directory and other build files in the `project` directory.

Follow these [`sbt` installation instructions](http://www.scala-sbt.org/release/docs/Getting-Started/Setup.html).

If you want to install Scala separately and Scala's *Scaladocs*, go to the [scala-lang.org _Getting Started_ guide](https://docs.scala-lang.org/scala3/getting-started.html) for details. However, this isn't required.

If you want to play with the Spark example, `src/script/scala-2/progscala3/bigdata/SparkWordCount.scala`, you'll need to download a Spark distribution from https://spark.apache.org. Assuming that `$SPARK_HOME` refers to the root directory of your Spark installation, run the following command in the root directory of this project:

```
$ $SPARK_HOME/bin/spark-shell
...
scala>
```

Then copy and paste the content of `src/script/scala-2/progscala3/bigdata/SparkWordCount.scala` at the prompt. After it runs, there will be a new directory, `README.md.wordcount` with the _partition_ files of the output.

> **Tip:** For more on Spark, see my free tutorial on GitHub, [spark-scala-tutorial](https://github.com/deanwampler/spark-scala-tutorial).

### Editors, IntelliJ, Visual Studio Code, and Other IDEs

> **NOTE:** Support for Scala 3 may be limited for a while in the following tools.

Most editors and IDEs now have some sort of Scala support:

* [IntelliJ](https://www.jetbrains.com/idea/): Either the Community or Ultimate additions will work. Install the Scala plugin, which has built-in support for `sbt`.
* [Visual Studio Code](https://code.visualstudio.com/): Use the new [Scala Metals](https://scalameta.org/metals/) plugin instead of older plugins.
* [Eclipse Scala IDE](http://scala-ide.org): Old, no longer recommended.

For other IDEs and text editors, try [Scala Metals](https://scalameta.org/metals/) first (I've used it with [Sublime Text](https://www.sublimetext.com/), for example) or the older [ENSIME](http://ensime.github.io/) project. You may also need additional, third-party tools for syntax highlighting, etc.

After installing the required plugins, load this project in your IDE, which should detect and use the `sbt` project automatically. For eclipse, run the `sbt eclipse` task to generate project files, then import them.

### Using Scala Worksheets

If you like working with _Scala worksheets_ in your IDE or editor, you may be able to load any of the REPL "script" files under `src/script` as a worksheet. If your environment is more restrictive, for example about the file extension used, then run the included `bash` script `./make-worksheets.sh` to copy all the REPL "script" examples to worksheet files. This command copies the tree `src/script` to `src/worksheet` and changes the `.scala` extension for all the files to `.worksheet.sc`, the VSCode convention. These behaviors are configurable. Use the `--help` option to see the details. If you are using Windows and you don't have `bash` available, e.g., through the Linux subsystem, then modify individual files as you see fit.

See this [Dotty documentation page](https://dotty.epfl.ch/docs/usage/worksheet-mode.html) for more information about worksheets.

## Building the Code Examples

After installing `sbt`, open a command/terminal window and run the `sbt test` command. 

You'll see lots of output as it downloads all the dependencies, compiles the code and runs the tests. You should see `[success]` messages at the end.

`sbt` is discussed in more detail in the book and the [`sbt` website](https://www.scala-sbt.org/), but a few useful commands are worth mentioning here.

If you start `sbt` without any arguments, it puts you into an interactive mode where you can type commands. Use control-D to exit this mode. Once at the `sbt` prompt (`sbt:programming-scala-3rd-ed-code-examples>`), try the following commands, where each `#` starts a comment; don't type those!

```
help       # help on tasks and settings
clean      # delete all build outputs
compile    # compile the source, but not test code
test       # compile source and test code, if necessary and run the tests.
~test      # continuously compile and test when source changes are saved.
console    # run the Scala REPL; dependencies and code are on the CLASSPATH
tasks      # show the most common tasks (commands).
tasks -V   # REALLY show ALL tasks
```

The `~` prefix causes the task to be run continuously each time source code changes are saved. This promotes continuous TDD (test-driven development) and is one of my favorite features!

Outside of `sbt`, you could, in principle, run the REPL and load the script files manually at the prompt:

```
$ scala
scala> :load src/script/scala/.../Foo.scala
```

However, it's easier to run most of the scripts using `sbt console`, because `sbt` will configure the `CLASSPATH` with the third-party libraries and compiled code examples that a script file might use.

Also, new for the Scala 3 REPL, for those `src/main/...` files that define one (and only one) _entry point_, meaning a `main` method (Scala 2 compatible) or annotated with `@main` (new Scala 3 technique), you can compile and run them in one step:

```
$ scala src/main/scala/progscala3/introscala/UpperMain2.scala Hello World!
HELLO WORLD!
$
```

## Feedback

I welcome feedback on the Book and these examples. Please post comments, corrections, etc. to one of the following places:

* This GitHub repo's [Gitter channel](https://gitter.im/deanwampler/programming-scala-book-code-examples), [Discussion forum](https://github.com/deanwampler/programming-scala-book-code-examples/discussions), or [Issues](https://github.com/deanwampler/programming-scala-book-code-examples/issues).
* The book's Twitter account, [@ProgScala](https://twitter.com/ProgScala).
* The O'Reilly book and errata sites (coming soon).

There is also my dedicated site for the book where occasional updates, clarifications, corrections, and lame excuses will be posted: [programming-scala.org](http://programming-scala.org).

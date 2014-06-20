# Programming Scala, 2nd Edition#
## README for the Code Examples ##

Dean Wampler
May 1, 2014

This archive contains all the code examples found in [Programming Scala, Second Edition](http://shop.oreilly.com/product/9780596155964.do), with the exception of some trivial code snippets in the text. There are also some examples in this distribution that aren't actually in the book. In the book's text, when an example corresponds to a file in this distribution, the listing begins with a path in a comment with the following format:

```
// src/main/scala/chapter/../filename
```

(Tests are in `src/test/scala/...`.)

Use these comments to find the corresponding source file. This archive also contains *ScalaTest* and *ScalaCheck* unit tests to validate some of the code. Most of these tests are not reproduced in the text of the book, except when discussing testing itself.

## Naming Conventions

The examples include "scripts" that are run with the `scala` command (or within SBT using the `console`), source files that are compiled with `scalac`, and source files that deliberately fail to compile to demonstrate common errors. To keep then straight and to support building with SBT, the following naming conventions are used for the files.

- `build.sbt` - The SBT build script (described below).
- `*.scala` - Source files that are compiled with `scala`. In fact, this is the community-standard extension for all Scala files, code to be compiled or scripts. But to keep the build process simple, I use different conventions for files that aren't compiled, discussed next. 
- `*.sc` - Script files that are executed directory, e.g., `scala foo-script.sc`. This file extension is not a standard, but it is used by the newer IDE *worksheet* feature I discuss in the the book. So, I stole the convention; SBT will ignore these scripts when compiling. These script don't have tests to verify them (TODO).
- `*.scalaX` and `*.scX` - Scala code files and scripts with deliberate errors, so they don't compile and run. They contain comments explaining what's wrong with them.

## Required and Optional Tools

To build and run the examples, all you need to do is install [SBT](http://www.scala-sbt.org/release/docs/Getting-Started/Setup.html). SBT is the de-facto standard build tool for Scala. When you run SBT, it will bootstrap itself with the correct version of its jar file, Scala, and project dependencies, which are specified in the `build.sbt` file in the root directory and other build files in the `project` directory.

Follow these [installation instructions](http://www.scala-sbt.org/release/docs/Getting-Started/Setup.html).

If you want to install Scala separate and the the *Scaladocs* (recommended), go to [scala-lang.org](http://scala-lang.org), but this isn't strictly required.

### Editors, Eclipse, and IntelliJ

Most editors now include Scala support or plugins are available. Similarly, there are plugins for Eclipse and IntelliJ. See their respective documentation for details on using Scala. 

Note that IntelliJ can import the SBT project for this example code. For eclipse, run `sbt eclipse` to generate project files, then important them. For both IDEs, the Scala plugin must be installed first.

## Building the Code Examples

After installing SBT, open a command/terminal window and run the `sbt test` command.
	
There will be a *lot* of output as it downloads all the dependencies, both internal to the tools and explicitly defined for the code examples.

After resolving dependencies, the code will be compiled and tested. You should see only success messages.

SBT is discussed in more detail in the book, but a few other commands are worth mentioning here.

If you start `sbt` without any arguments, it puts you into an interactive mode where you can type commands. Use control-D to exit this mode. Once at the SBT prompt (a `>`), try the following commands, where each `#` starts a comment; don't type those!

	help       # help on tasks and settings
	clean      # delete all build outputs
	compile    # compile the source, but not test code
	test       # compile source and test code, if necessary and run the tests.
	+test      # compile and test for all versions of Scala supported.
	~test      # continuously compile and test when source changes are saved.
	tasks      # show the most common tasks (commands). 
	tasks -V   # REALLY show ALL tasks

Note the `+test` example. It looks at a property named `crossScalaVersions` in the build file, `build.sbt` to know which versions of Scala to use. The `+` can be used for any task, although for some it will make little sense. Similarly, the `~` prefix causes the task to be run continuously each time source code changes are saved. This promotes continuous TDD (test-driven development) is one of my favorite features!

Exiting from SBT, you can run the script files manually at the console/terminal prompt.

    scala foo.sc
    
Some examples require code that has been compiled and some may require additional arguments, either to pass to `scala` or to the script itself. The book and the comments in the files themselves will provide the details.

For example, if build artifacts are required on the class path, use the following command from the *root* directory of the distribution:

    scala -classpath target/scala-2.11/classes /path/to/foo.sc
    
As configured, the build defaults to Scala 2.11.X. Hence, SBT directs compiled output to `target/scala-2.11/classes`. 

## Feedback ##

I welcome feedback on the examples. Please post comments, corrections, etc. at the books site, [Programming Scala, Second Edition](http://shop.oreilly.com/product/9780596155964.do), or you can post feedback on the [O'Reilly forum](http://forums.oreilly.com/). There is also a dedicated site for the book where occasional updates, clarifications, and corrections will be posted: [programming-scala.org](http://programming-scala.org).

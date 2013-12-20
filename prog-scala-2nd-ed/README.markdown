# Programming Scala, 2nd Edition#
## README for the Code Examples ##

Dean Wampler
January 31, 2014

This archive contains all the code examples found in [Programming Scala, Second Edition](http://shop.oreilly.com/product/9780596155964.do) (with the exception of some trivial code snippets in the text). In the book, all the examples for which there are corresponding files here begin with a path comment like this:

    // chapter/../filename
   
Use these comments to find the corresponding file in this distribution. In addition, this archive contains *ScalaTest* and *ScalaCheck* unit tests to validate the code, most of which are not reproduced in the text of the book, except when discussing testing itself; see for example the _Tools and Libraries_ chapter of the book.

## Naming Conventions

The examples include "scripts" that are run with the `scala` command, source files that are compiled with `scalac`, and source files that deliberately fail to compile to demonstrate common errors. To keep then straight and to support the build process, the following naming conventions are used for the files.

- `build.sbt` - The SBT build script (described below).
- `*.scala` - Source files that are compiled with `scalar`. In fact, this is the community-standard extension for all Scala files, code to be compiled or scripts. But to keep the build process simple, I use different conventions for other files, discussed next. 
- `*.sc` - Script files that executed directory, e.g., `scala foo-script.sc`. This file extension is not a standard, but it is used by the newer IDE *worksheet* feature we discuss in the first chapter. So, we stole the convention; the SBT compile process will ignore these scripts. These script don't have tests to verify them.
- `*.scalaX` and `*.scX` - Scala code files and scripts with deliberate errors, so they don't compile and run. They do contain comments explaining what's wrong with them. The `X` suffix means the build will ignore them.
- `*-v2N-*` - Scala version 2.N only. Some features only work with particular Scala versions. These files demonstrate these features.

## Required and Optional Tools

To build and run the examples, all you need to do is install SBT driver script. SBT is the de-facto standard build tool for Scala. When you run SBT, it will bootstrap itself with the correct version of its jar file, Scala, and project dependencies, which are specified in the `build.sbt` file in the root directory and other build files in the `project` directory.

Follow these [installation instructions](http://www.scala-sbt.org/release/docs/Getting-Started/Setup.html).

If you want to install Scala separate, e.g., to also grab the *Scaladocs*, go to [scala-lang.org](http://scala-lang.org), but this isn't strictly required.

### Editors, Eclipse, and IntelliJ

Most editors now include Scala support or plugins are available. Similarly, there are plugins for Eclipse and IntelliJ. 

## Building the Code Examples

After installing SBT, open a command/terminal window and run the `sbt test` command.
	
There will be a *lot* of output as it downloads all the dependencies, both internal to the tools and explicitly defined for the code examples.

After resolving dependencies, the code will be compiled and tested. You should see only success messages.

SBT is discussed in more detail in the book, but a few other commands are worth mentioning here.

If you start `sbt` without any arguments, it puts you into an interactive mode where you can type commands. Use control-D to exit this mode. Once at the SBT prompt (a `>`), try the following commands, where each `#` starts a comment; don't type those!

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

    scala -classpath target/scala-2.10/classes /path/to/foo.sc
    
Note that SBT directs compiled output to `target/scala-2.10/classes` by default. For Scala version 2.11 builds, replace the `10` by an `11`. 

## Feedback ##

I welcome feedback on the examples. Please post comments, corrections, etc. at the books site, [Programming Scala, Second Edition](http://shop.oreilly.com/product/9780596155964.do), or you can post feedback on the [O'Reilly forum](http://forums.oreilly.com/). There is also a dedicated site for the book where occasional updates, clarifications, and corrections will be posted: [programming-scala.org](http://programming-scala.org).

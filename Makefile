# This GNU Makefile is used for tasks that are tedious with SBT itself.
# Currently, it is just used to automate testing of the "script" files.
#
# Notes:
# 1. Uses a script 'misc/determine_classpath.sh' to run SBT to extract the full CLASSPATH,
#    which it caches in 'misc/classpath.txt'. If you change the CLASSPATH in SBT, e.g.,
#    change a library version, then delete this text file and rebuild the golden files.
# 2. While the CLASSPATH is determined from SBT, the other 'scala' options are hard-coded
#    below. If you change these options in SBT, manually change them here, too!

.ONESHELL:
SHELL = /bin/bash
.SHELLFLAGS = -o pipefail

output_dir    := output
script_files  := $(shell find src/main/scala -name '*.sc')
test_logs     := $(script_files:src/main/%.sc=$(output_dir)/%.log)

classpath := $(shell misc/determine_classpath.sh)

# Most of the same options used in the SBT build.
scala2_options_base = \
  -Xfatal-warnings \
  -Xlint:constant \
  -deprecation \
  -encoding \
  utf-8 \
  -unchecked \
  -feature \
  -explaintypes \
  -language:existentials \
  -language:higherKinds \
  -language:implicitConversions \
  -Ywarn-dead-code \
  -Ywarn-numeric-widen \
  -Xcheckinit \
  -Xlint:adapted-args \
  -Xlint:delayedinit-select \
  -Xlint:doc-detached \
  -Xlint:inaccessible \
  -Xlint:missing-interpolator \
  -Xlint:nullary-override \
  -Xlint:nullary-unit \
  -Xlint:option-implicit \
  -Xlint:package-object-classes \
  -Xlint:poly-implicit-overload \
  -Xlint:private-shadow \
  -Xlint:stars-align \
  -Xlint:type-parameter-shadow \
  -classpath $(classpath)

scala2_options := \
  -Ywarn-extra-implicit \
  -Ywarn-unused:implicits \
  -Ywarn-unused:imports \
  -Ywarn-unused:locals \
  -Ywarn-unused:privates \
  -Ydelambdafy:inline \
  $(scala_options_base)

# Options used in the SBT build, but not used here for the scripts:
scala2_options_unused = \
  -Xlint:infer-any \
  -Ywarn-unused:params \
  -Ywarn-value-discard

SCALA := dotr
scala_options :=
#SCALA := scala
#scala_options := $(scala2_options)

all: clean tests

clean:
	@rm -rf $(output_dir)

tests: $(test_logs)

# Test each script by running it. The log will be mostly empty
# of output, often just containing the command that was run.
$(output_dir)/%.log: src/main/%.sc
	@echo "Testing: $< (output: $@) ..." 1>&2
	@mkdir -p $$(dirname $@)
	@echo $(SCALA) $(scala_options) $< > $@
	@$(SCALA) $(scala_options) $< >> $@

# Run the Scala interpeter with the "base" settings.
console:
	@scala $(scala_options_base)

# Targets to show information.

show_scala_options_base:
	@echo $(scala_options_base)
show_scala_options:
	@echo $(scala_options)
show_classpath:
	@echo $(classpath)
show_scripts:
	@for f in $(script_files); do echo $$f; done		
show_test_logs:
	@for f in $(test_logs); do echo $$f; done

# This GNU Makefile is used for tasks that are tedious with SBT itself.
# Currently, it is just used to automate testing of the "script" files:
# 1. Run all the '*.sc' scripts and capture their outputs as "golden" text files.
#    See warning note below.
# 2. Run all the '*.sc' scripts and capture their outputs and compare to the golden files.
#    Run this periodically to verify that the scripts still work as expected.
# 3. ...
#
# Notes:
# 1. Memory addresses are "rationalized", e.g., FooBar@1a2b3c4f becomes FooBar@XXXXXXXX,
#    so that these trivial differences are ignored between different runs.
# 2. Uses a script 'misc/determine_classpath.sh' to run SBT to extract the full CLASSPATH,
#    which it caches in 'misc/classpath.txt'. If you change the CLASSPATH in SBT, e.g.,
#    change a library version, then delete this text file and rebuild the golden files.
# 3. While the CLASSPATH is determined from SBT, the other 'scala' options are hard-coded
#    below. If you change these options in SBT, manually change them here, too!

.ONESHELL:
SHELL = /bin/bash
.SHELLFLAGS = -o pipefail

script_output_dir := output
script_files := $(shell find src/main/scala -name '*.sc')
golden_output_files := $(script_files:src/main/%.sc=src/test/%.golden.txt)
output_files := $(script_files:src/main/%.sc=$(script_output_dir)/%.txt)
output_diffs := $(output_files:%.txt=%.diff)

classpath := $(shell misc/determine_classpath.sh)

# Most of the same options used in the SBT build.
scala_options_base = \
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

scala_options := \
  -Ywarn-extra-implicit \
  -Ywarn-unused:implicits \
  -Ywarn-unused:imports \
  -Ywarn-unused:locals \
  -Ywarn-unused:privates \
  -Xfatal-warnings \
  $(scala_options_base)

# Options used in the SBT build, but not used here for the scripts:
scala_options_unused = \
  -Xlint:infer-any \
  -Ywarn-unused:params \
  -Ywarn-value-discard

# Does NOT build the golden output files. If they don't exist or need updating,
# run 'make new_golden_files' first (but read the WARNING for that target...).
all: verify_scripts

verify_scripts: clean $(output_diffs)

clean:
	@rm -rf $(script_output_dir)

%.diff: %.txt
	@echo "Making $@ ..."
	@mkdir -p $$(dirname $@)
	@diff $< ${<:src/main/%.sc=src/test/%.golden.txt} > $@ || { echo "$@ isn't empty!"; exit 1; }

# WARNING: If you build the following target, you will have to
# manually verify that ALL the outputs are still correct!
new_golden_files: clean_golden_files make_golden_files

clean_golden_files:
	@rm -f $(golden_output_files)

make_golden_files: $(golden_output_files)

# Create the output and the "golden" output files. 
# To get the full output and to have it parsed correctly, we use a hack
# where we first feed a `:paste` to the interpreter, then all the script
# lines, then let it compile at one (effectively).
# Also, we have to fix strings that vary from run to run, mostly memory
# addresses.
# WARNING: Does NOT error out if the script fails to parse!
$(script_output_dir)/%.txt src/test/%.golden.txt: src/main/%.sc
	@echo "Making $@ ..."
	@mkdir -p $$(dirname $@)
	@{ echo ':paste'; cat $<; } | scala $(scala_options) | sed \
		-e 's/@[0-9a-fA-F]\{1,\}/@XXXXXXXX/g' \
		-e 's/..Lambda.[0-9]\{1,\}\/0x[0-9a-fA-F]\{1,\}/Lambda@XXXXXXXX/g' > $@

# Convenience target that doesn't write the text to a file, just stdout,
# so the "*.notext" target is effectively phony.
# WARNING: Does NOT error out if the script fails to parse!
$(script_output_dir)/%.notxt: src/main/%.sc
	@echo "Making $@ ..."
	@mkdir -p $$(dirname $@)
	@{ echo ':paste'; cat $<; } | scala $(scala_options_base) | sed \
		-e 's/@[0-9a-fA-F]\{1,\}/@XXXXXXXX/g' \
		-e 's/..Lambda.[0-9]\{1,\}\/0x[0-9a-fA-F]\{1,\}/Lambda@XXXXXXXX/g' 

# Run the Scala interpeter with the "base" settings.
console:
	@scala $(scala_options_base)

# Targets to echo information.

scala_options_base:
	@echo $(scala_options_base)
scala_options:
	@echo $(scala_options)
classpath:
	@echo $(classpath)

scripts:
	@for f in $(script_files); do echo $$f; done
		
golden_output_files golden_files:
	@for f in $(golden_output_files); do echo $$f; done

output_files:
	@for f in $(output_files); do echo $$f; done

output_diffs:
	@for f in $(output_diffs); do echo $$f; done

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

script_output_dir   := output
script_files        := $(shell find src/main/scala -name '*.sc')
golden_output_files := $(script_files:src/main/%.sc=src/test/%.golden.txt)
output_files        := $(script_files:src/main/%.sc=$(script_output_dir)/%.txt)
output_diffs        := $(output_files:%.txt=%.diff)
test_targets        := $(script_files:src/main/%.sc=$(script_output_dir)/%.test)

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
  $(scala_options_base)

# Options used in the SBT build, but not used here for the scripts:
scala_options_unused = \
  -Xfatal-warnings \
  -Xlint:infer-any \
  -Ywarn-unused:params \
  -Ywarn-value-discard

# Does NOT build the golden output files. If they don't exist or need updating,
# run 'make all_golden_files' first (but read the WARNING for that target...).
all: verify_scripts

verify_scripts: clean $(output_diffs)

clean:
	@rm -rf $(script_output_dir)

%.diff: %.txt
	@echo "Making $@ ..."
	@mkdir -p $$(dirname $@)
	@diff $< ${<:src/main/%.sc=src/test/%.golden.txt} > $@ || { echo "$@ isn't empty!"; exit 1; }

# WARNING: If you build the following target, you will have to
# manually verify that ALL the outputs are still correct! See 
# the show_golden_warnings_errors target for help.
# There should be no actual errors, but you will see compiler 
# warnings about deprecated constructs, etc. All those occurrences
# should have code comments telling you to expect them.
all_golden_files: clean_golden_files make_golden_files

clean_golden_files:
	@rm -f $(golden_output_files)

make_golden_files: $(golden_output_files)

do_test_runs: $(test_log_files)

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

test: $(test_targets)

# Test all the scripts by running them. The commands here don't pipe the
# script into stdin, so they also don't echo much to stdout. But this target
# works more reliable for failing when a compilation fails or an assertion 
# is thrown.
$(script_output_dir)/%.test: src/main/%.sc
	@echo "Testing: $@ ..."
	@mkdir -p $$(dirname $@)
	scala $(scala_options) $< 

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

# Targets to show information.

# This target shows all occurrences of the word "error" (ignoring case)
# in the golden files. Some are expected...
show_golden_warnings_errors:
	@for f in $(golden_output_files); \
	do echo "====== $$f"; \
	grep -i 'error\|warning' $$f; \
	done

show_scala_options_base:
	@echo $(scala_options_base)
show_scala_options:
	@echo $(scala_options)
show_classpath:
	@echo $(classpath)

show_scripts:
	@for f in $(script_files); do echo $$f; done
		
show_golden_output_files show_golden_files:
	@for f in $(golden_output_files); do echo $$f; done

show_output_files:
	@for f in $(output_files); do echo $$f; done

show_output_diffs:
	@for f in $(output_diffs); do echo $$f; done

show_test_targets:
	@for f in $(test_targets); do echo $$f; done

# Are there golden files or scripts for which the companion doesn't exist??
show_golden_script_mismatch:
	@for f in $(golden_output_files); \
	do f2=$${f/src\/test/src\/main}; \
	f3=$${f2/golden.txt/sc}; \
	[[ -f $$f  ]] || echo $$f3; \
	[[ -f $$f3 ]] || echo $$f; \
	done

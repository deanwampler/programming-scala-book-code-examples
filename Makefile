# This GNU Makefile is used for tasks that are tedious with SBT itself:
# 1. Run all the '*.sc' scripts and capture their outputs as "golden" text files.
#    See warning note below.
# 2. Run all the '*.sc' scripts and capture their outputs and compare to the golden files.
#    Run this periodically to verify that the scripts still work as expected.
# 3. ...
#
# Run `make all` before building with SBT.
# Notes:
# 1. Memory addresses are "relationalize", e.g., FooBar@1a2b3c4f becomes FooBar@XXXXXXXX,
#    so that diffs work between different runs.

script_output_dir := output
script_files := $(shell find src/main/scala -name '*.sc')
golden_output_files := $(script_files:src/main/%.sc=src/test/%.golden.txt)
output_files := $(script_files:src/main/%.sc=$(script_output_dir)/%.txt)
output_diffs := $(output_files:%.txt=%.diff)

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
  -Xlint:type-parameter-shadow

scala_options := $(scala_options_base) \
  -Ywarn-extra-implicit \
  -Ywarn-unused:implicits \
  -Ywarn-unused:imports \
  -Ywarn-unused:locals \
  -Ywarn-unused:privates \
  -Xfatal-warnings

# Options used in the SBT build, but not for the scripts:
scala_options_unused = \
  -Xlint:infer-any \
  -Ywarn-unused:params \
  -Ywarn-value-discard

all: verify_scripts

verify_scripts: clean_output $(output_diffs)

clean_output:
	@rm -rf $(script_output_dir)

%.diff: %.txt
	@echo "Making $@ ..."
	@mkdir -p $$(dirname $@)
	@diff $< ${<:src/main/%.sc=src/test/%.golden.txt} > $@ || { echo "$@ isn't empty!"; exit 1; }

# WARNING: If you build the following target, you will have to
# manually verify that ALL the outputs are still correct!
new_golden_files: clean run-scripts

clean:
	@rm -f $(golden_output_files)

run-scripts: $(golden_output_files)

# Create the output and the "golden" output files. 
# To get the full output and to have it parsed correctly, we use a hack
# where we first feed a `:paste` to the interpreter, then all the script
# lines, then let it compile at one (effectively).
# Also, we have to fix strings that vary from run to run, mostly memory
# addresses.
$(script_output_dir)/%.txt src/test/%.golden.txt: src/main/%.sc
	@echo "Making $@ ..."
	@mkdir -p $$(dirname $@)
	@{ echo ':paste'; \
		cat $<; \
	} | scala $(scala_options) | sed \
		-e 's/@[0-9a-fA-F]\{1,\}/@XXXXXXXX/g' \
		-e 's/..Lambda.[0-9]\{1,\}\/0x[0-9a-fA-F]\{1,\}/Lambda@XXXXXXXX/g' > $@

# Doesn't write the text to a file, just stdout.
$(script_output_dir)/%.notxt: src/main/%.sc
	@echo "Making $@ ..."
	@mkdir -p $$(dirname $@)
	@{ echo ':paste'; \
		cat $<; \
	} | scala $(scala_options_base) | sed \
		-e 's/@[0-9a-fA-F]\{1,\}/@XXXXXXXX/g' \
		-e 's/..Lambda.[0-9]\{1,\}\/0x[0-9a-fA-F]\{1,\}/Lambda@XXXXXXXX/g' 

# Targets to echo information:
scala_options_base:
	@echo $(scala_options_base)
scala_options:
	@echo $(scala_options)

console:
	@scala $(scala_options_base)

scripts:
	@for f in $(script_files); do echo $$f; done
		
golden_output_files golden_files:
	@for f in $(golden_output_files); do echo $$f; done

output_files:
	@for f in $(output_files); do echo $$f; done

output_diffs:
	@for f in $(output_diffs); do echo $$f; done

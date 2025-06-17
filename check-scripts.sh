#!/usr/bin/env bash

default_dirs=( "src/script/scala" )
out_root="target/script-tests"
out_ext="out"
timestamp=$(date +"%Y-%m-%d_%H-%M-%S")
expected_errors_in=(

  src/script/scala/progscala3/IndentationSyntax.scala
  src/script/scala/progscala3/appdesign/Deprecated.scala
  src/script/scala/progscala3/basicoop/MatchableOpaque.scala
  src/script/scala/progscala3/collections/MultiMap.scala
  src/script/scala/progscala3/contexts/ImplicitEvidence.scala
  src/script/scala/progscala3/contexts/ImplicitNotFound.scala
  src/script/scala/progscala3/contexts/MatchGivens.scala
  src/script/scala/progscala3/contexts/SeqUnzip.scala
  src/script/scala/progscala3/dynamic/SelectableSQL.scala
  src/script/scala/progscala3/meta/compiletime/RequireConst.scala
  src/script/scala/progscala3/meta/compiletime/SummonAll.scala
  src/script/scala/progscala3/meta/inline/ConditionalMatch.scala
  src/script/scala/progscala3/meta/inline/Overrides.scala
  src/script/scala/progscala3/meta/inline/Recursive.scala
  src/script/scala/progscala3/objectsystem/variance/MutableVariance.scala
  src/script/scala/progscala3/patternmatching/Matchable.scala
  src/script/scala/progscala3/patternmatching/MatchExhaustive.scala
  src/script/scala/progscala3/patternmatching/MatchForFiltering.scala
  src/script/scala/progscala3/patternmatching/MatchSurprise.scala
  src/script/scala/progscala3/patternmatching/MatchTypesErasure.scala
  src/script/scala/progscala3/patternmatching/UnapplySingleValue2.scala
  src/script/scala/progscala3/rounding/InfixMethod.scala
  src/script/scala/progscala3/rounding/InfixType.scala
  src/script/scala/progscala3/rounding/TypeErasureProblem.scala
  src/script/scala/progscala3/typelessdomore/Human.scala
  src/script/scala/progscala3/typelessdomore/MethodBroadInference.scala
  src/script/scala/progscala3/typelessdomore/MethodNestedReturn.scala
  src/script/scala/progscala3/typelessdomore/MethodRecursiveReturn.scala
  src/script/scala/progscala3/typelessdomore/RepeatedParameters.scala
  src/script/scala/progscala3/typesystem/bounds/ViewToContextBounds.scala
  src/script/scala/progscala3/typesystem/deptypes/DependentTypes.scala
  src/script/scala/progscala3/typesystem/deptypes/DependentTypesBounds.scala
  src/script/scala/progscala3/typesystem/deptypes/DependentTypesSimple.scala
  src/script/scala/progscala3/typesystem/intersectionunion/Intersection.scala
  src/script/scala/progscala3/typesystem/intersectionunion/Union.scala
  src/script/scala/progscala3/typesystem/matchtypes/MatchTypes2.scala
  src/script/scala/progscala3/typesystem/typepaths/TypePath.scala
  src/script/scala/progscala3/typesystem/valuetypes/SingletonTypes.scala
  src/script/scala/progscala3/typesystem/valuetypes/TypeProjection.scala
)

error() {
  echo "ERROR: $@"
  help
  exit 1
}

help() {
	cat << EOF
Checks the script files by loading them in the interpreter. Note that Scala 3
changes the way "scripts" are interpreted. They are now expected to have @main
methods (or old-style main methods), while the "scripts" in this repo are really
just intended for interactive sessions. Hence, ":load file" is used here. There
are files with @main methods under src/main that can be interpreted as Scala 3
"scripts" by the REPL, in which case you just pass the path as a command-line
argument.

So, this bash script starts the REPL (using "sbt console") for each file and then
uses :load to load the file. The output for that console sessions is written to
$out_root/path/to/file.$out_ext.

A list of files with errors or warnings is written to $out_root/errors-$timestamp.log.

The following files are known to throw errors intentionally:
$(for f in ${expected_errors_in[@]}; do echo "  $f"; done)

Failures for these known files are ignored, but logged in $out_root/errors-$timestamp.log. 
In most of them, you'll see a comment on the same line, like "// ERROR" or "// COMPILATION ERROR", 
which are easier to spot when looking at error messages. In the rest of the cases, you have to
look at the book discussion to see if the error is expected. Unfortunately, this means that any 
unexpected errors in these files will be missed, unless you inspect the output carefully!

For finding unexpected errors, the console output is searched for errors by looking
for any of the following lines near the end (where N=2+):

1 warning found
N warnings found
1 error found
N errors found

** HOWEVER, to be really safe, all the outputs should still be inspected manually. **

Usage: $0 [-h|--help] [-v|--verbose] [-c|--clean] [-n|--no-exec] [dir ...]
Where:
-h | --help       Print this message and exit.
-v | --verbose    Print each file name to the console as it is processed and dump
                  to stdout the test output (in the script's corresponding 
                  "target/script-tests/...").
-c | --clean      Delete all previous output.
-n | --no-exec    Don't execute the commands, just echo what would be done.
--check | --check-only  
                  Don't run the scripts; just check for reported errors only
                  on any existing output files under $out_root.
dir ...           Start in these directories. (default "${default_dirs[@]}")
EOF
}

: ${VERBOSE:=false}
: ${CLEAN:=false}
: ${CHECK_ONLY=false}
: ${NOOP:=}
dirs=()

while [[ $# -gt 0 ]]
do
	case $1 in
		-h|--h*)
			help
			exit 0
			;;
		-v|--v*)
			VERBOSE=true
			;;
    --check*)
      CHECK_ONLY=true
      ;;
    -c|--cl*)
      CLEAN=true
      ;;
    -n|--n*)
      NOOP=echo
      ;;
		-*)
			error "Unknown argument $1"
			;;
		*)
			dirs+=($1)
			;;
	esac
	shift
done

[[ ${#dirs[@]} -gt 0 ]] || dirs=( ${default_dirs[@]} )
$VERBOSE && echo "Reading directories ${dirs[@]}"

if $CLEAN
then
  $VERBOSE && echo "Cleaning old output in $out_root..."
  [[ -n "$out_root" ]] && rm -rf "$out_root"  # safety check!
fi


error_log="$out_root/errors-$timestamp.log"
rm -f $error_log

print_count() {
  let count=$1; shift
  file=$1; shift
  out=$1; shift
  message="$1"; shift
  printf '%5d: %s %s %s\n' $count "$file" "$out" "$message" >> $error_log
}

count_problem() {
  script=$1
  out=$2
  let count=$(grep -cE "^.+ (error|warning)s? found$" "$out")
  [[ $count -gt 0 ]] && print_count $count $script $out 
  return $count
}

report() {
  let status=$1
  script=$2
  out=$3
  for skip in ${expected_errors_in[@]}
  do
    if [[ "$skip" = "$script" ]]
    then
      print_count 0 "$script" "$out" "NOTE: because of known deliberate errors, unexpected errors might be missed!"
      return 0
    fi
  done
  let error_count=0
  if [[ $status -ne 0 ]]
  then
    echo "ERROR: $script failed! ($out)"
    let error_count+=1
  fi
  count_problem "$script" "$out"
  let error_count+=$?
  # $VERBOSE && cat "$out"
  return $error_count
}

export total_problem_count
let total_problem_count=0

check() {
  script="$1"
  out="$out_root/$script.$out_ext"
  $VERBOSE && echo "$f --> $out"
  if ! $CHECK_ONLY
  then
    $NOOP rm -f "$out"
    if [[ -z "$NOOP" ]]
    then
      mkdir -p $(dirname "$out")
      TERM=dumb sbt console <<EOF > "$out"
:load $script
EOF
    else
      $NOOP mkdir -p $(dirname $out)
      $NOOP "TERM=dumb sbt console ... :load $script ... > $out"
    fi
  fi
  $NOOP report $? "$script" "$out"
  let total_problem_count+=$? 
  # return $?
}

problem_count="$out_root/problem_count.txt" # see "hack" note below.
rm -f "$problem_count"
for dir in "${dirs[@]}"
do
	find "$dir" -name '*.scala' | while read f
	do
    check $f
    # hack! The value of total_problem_count is lost to the outer shell,
    # so write the values to a file for consumption "outside".
    echo $total_problem_count >> "$problem_count"
  done
done

if [[ -f "$problem_count" ]]
then
  let total_problem_count=$(tail -n 1 "$problem_count")
  rm -f "$problem_count"
  if [[ $total_problem_count -gt 0 ]]
  then
    echo "ERROR: $total_problem_count issues found. See $error_log"
    print_count $total_problem_count $error_log "" "issues found!"
    exit 1
  fi
fi
echo "No obvious issues found, but consider checking all the output files in $out_root!"
exit 0


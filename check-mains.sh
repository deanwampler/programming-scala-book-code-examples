#!/usr/bin/env zsh

out_root="target/main-tests"
out_ext="out"
timestamp=$(date +"%Y-%m-%d_%H-%M-%S")
error_log="$out_root/mains-errors-$timestamp.log"
def_mains=(
  ScriptWrapper
  progscala3.appdesign.IntDoubleStringMain
  progscala3.appdesign.dbc.TryBankAccount
  progscala3.appdesign.dbc.TryMyLogger
  progscala3.appdesign.parthenon.RunPayroll
  progscala3.basicoop.HelloServiceMain
  progscala3.basicoop.TryComplex
  progscala3.basicoop.scaladb.TryScalaDBRevisited
  progscala3.basicoop.tagging.TryTagging
  progscala3.basicoop.tagging.TryTagging2
  progscala3.collections.TryListBuilder
  progscala3.concurrency.akka.ServiceClient
  progscala3.concurrency.boundary.BoundaryExamples
  progscala3.concurrency.futures.TryFutureFold
  progscala3.concurrency.futures.TryFuturesCallbacks
  progscala3.concurrency.futures2.TryFuturesForComp
  progscala3.concurrency.process.TryProcess
  progscala3.contexts.TryDerived
  progscala3.contexts.accounting.TryImplicitConversions
  progscala3.contexts.json.TryJSONBuilder
  progscala3.contexts.scaladb.TryScalaDB
  progscala3.contexts.typeclass.new1.TryJSONTypeClasses
  progscala3.contexts.typeclass.new2.TryJSONTypeClasses
  progscala3.contexts.typeclass.new3.TryJSONTypeClasses
  progscala3.contexts.typeclass.new4.TryJSONTypeClasses
  progscala3.contexts.typeclass.old.TryJSONTypeClasses
  progscala3.dsls.payroll.internal.TryPayroll
  progscala3.dsls.payroll.parsercomb.TryPayroll
  progscala3.forcomps.RemoveBlanks
  progscala3.forcomps.TryLoginFormValidatorNec
  progscala3.forcomps.TryLoginFormValidatorSingle
  progscala3.fp.categories.TryFunctionF2A
  progscala3.fp.categories.TryFunctionF2B
  progscala3.fp.categories.TryFunctionF2C
  progscala3.fp.categories.TryFunctionF2D
  progscala3.fp.categories.TryFunctor2
  progscala3.fp.loops.JavaFactorial
  progscala3.introscala.Hello
  progscala3.introscala.Hello2
  progscala3.introscala.UpperMain1
  progscala3.introscala.UpperMain1$package
  progscala3.introscala.shapes.ProcessShapesDriver
  progscala3.javainterop.JavaWithScalaTuples
  progscala3.meta.TryInvariant
  progscala3.meta.TryInvariant1
  progscala3.meta.TryStaging
  progscala3.meta.TryTracer
  progscala3.meta.TryUsingClassTagViews
  progscala3.meta.performance.InlinePerf
  progscala3.objectsystem.CommandArgs
  progscala3.objectsystem.JavaArrays
  progscala3.objectsystem.objects.TryPerson
  progscala3.rounding.FileSizes
  progscala3.rounding.TryCatch
  progscala3.rounding.TryCatchARM
  progscala3.rounding.saferexceptions.SaferExceptions
  progscala3.rounding.saferexceptions.SaferExceptionsNested
  progscala3.typesystem.intersectionunion.IntersectionUnion
  progscala3.typesystem.payroll.TryPhantomTypes
  progscala3.typesystem.payroll.TryPhantomTypesPipeline
  progscala3.typesystem.selftype.TryButtonSubjectObserver
)

declare -A mains_args
mains_args["progscala3.meta.performance.InlinePerf"]="true 10"
mains_args["progscala3.objectsystem.CommandArgs"]="--help"

expected_errors_in=(
  progscala3.meta.TryInvariant
  progscala3.meta.TryInvariant1
  progscala3.objectsystem.JavaArrays
)

error() {
  echo "ERROR: $@"
  help
  exit 1
}

help() {
	cat << EOF
Checks that the "mains" run successfully by running them with the "runMain" sbt command.

So, this bash script starts the REPL (using "sbt console") for each "main" and then
uses :runMain to execute it.  The output for that console sessions is written to
$out_root/path/to/file.$out_ext.

A list of files with errors or warnings is written to $error_log.

** HOWEVER, to be really safe, all the outputs should still be inspected manually. **

Usage: $0 [-h|--help] [-v|--verbose] [-c|--clean] [-n|--no-exec] [dir ...]
Where:
-h | --help       Print this message and exit.
-v | --verbose    Print each file name to the console as it is processed and dump
                  to stdout the test output (in the script's corresponding 
                  "$out_root/...").
-c | --clean      Delete all previous output.
-n | --no-exec    Don't execute the commands, just echo what would be done.
--check | --check-only  
                  Don't run the mains; just check for reported errors only
                  on any existing output files under $out_root.
main ...          Run these "mains". (default "${def_mains[@]}")
EOF
}

: ${VERBOSE:=false}
: ${CLEAN:=false}
: ${CHECK_ONLY=false}
: ${NOOP:=}
mains=()

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
			mains+=($1)
			;;
	esac
	shift
done

[[ ${#mains[@]} -gt 0 ]] || mains=( ${def_mains[@]} )
$VERBOSE && echo "Running mains: ${mains[@]}"

if $CLEAN
then
  $VERBOSE && echo "Cleaning old output in $out_root..."
  [[ -n "$out_root" ]] && rm -rf "$out_root"  # safety check!
fi

rm -f $error_log

print_count() {
  let count=$1; shift
  main=$1; shift
  out=$1; shift
  message="$1"; shift
  printf '%5d: %s %s %s\n' $count "$main" "$out" "$message" >> $error_log
}

count_problem() {
  main=$1
  out=$2
  let count=$(grep -cE "^.+ (error|warning)s? found$" "$out")
  [[ $count -gt 0 ]] && print_count $count $main $out 
  return $count
}

report() {
  let run_status=$1
  main=$2
  out=$3
  for skip in ${expected_errors_in[@]}
  do
    if [[ "$skip" = "$main" ]]
    then
      print_count 0 "$main" "$out" "NOTE: because of known deliberate errors, unexpected errors might be missed!"
      return 0
    fi
  done
  let error_count=0
  if [[ $run_status -ne 0 ]]
  then
    echo "ERROR: $main failed! ($out)"
    let error_count+=1
  fi
  count_problem "$main" "$out"
  let error_count+=$?
  # $VERBOSE && cat "$out"
  return $error_count
}

export total_problem_count
let total_problem_count=0

check() {
  main="$1"
  shift
  out="$out_root/$main.$out_ext"
  $VERBOSE && echo "$main --> $out"
  if ! $CHECK_ONLY
  then
    $NOOP rm -f "$out"
    if [[ -z "$NOOP" ]]
    then
      mkdir -p $(dirname "$out")
      TERM=dumb sbt "runMain $main $mains_args[\"$main\"] $@" > "$out"
    else
      $NOOP mkdir -p $(dirname $out)
      $NOOP "TERM=dumb sbt runMain $main $mains_args[\"$main\"] $@ > $out"
    fi
  fi
  $NOOP report $? "$main" "$out"
  let total_problem_count+=$? 
  # return $?
}

problem_count="$out_root/mains-problem-count.txt" # see "hack" note below.
rm -f "$problem_count"
for main in "${mains[@]}"
do
  check $main
  # hack! The value of total_problem_count is lost to the outer shell,
  # so write the values to a file for consumption "outside".
  echo $total_problem_count >> "$problem_count"
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


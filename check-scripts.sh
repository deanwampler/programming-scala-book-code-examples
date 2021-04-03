#!/usr/bin/env bash

default_dirs=( "src/script/scala" )
out_root="target/script-tests"
out_ext="out"

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
uses :load to load the file. The output is written to
$out_root/path/to/file.$out_ext.

Some files DO throw errors. In some cases, you'll see a comment on the same line
like "// ERROR". In other cases, you have to look at the book discussion to see
if the error is expected. Unfortunately, all the output has to be inspected manually.
If you see lots of errors for any one file, make sure you are using a Scala 3 REPL!

Usage: $0 [-h|--help] [-v|--verbose] [-c|--clean] [-n|--no-exec] [dir ...]
Where:
-h | --help       Print this message and exit.
-v | --verbose    Print each file name to the console as it is processed.
-c | --clean      Delete all previous output.
-n | --no-exec    Don't execute the commands, just echo what would be done.
dir ...           Start in these directories. (default "${default_dirs[@]}")
EOF
}

: ${VERBOSE:=false}
: ${CLEAN:=false}
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
    -c|--c*)
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

check() {
  script="$1"
  out="$out_root/$script.$out_ext"
  $NOOP rm -f $out
  $VERBOSE && echo "$f --> $out"
  if [[ -z "$NOOP" ]]
  then
    mkdir -p $(dirname $out)
    TERM=dumb sbt console <<EOF > $out
:load $script
EOF
  else
    $NOOP mkdir -p $(dirname $out)
    $NOOP "TERM=dumb sbt console ... :load $script ... > $out"
  fi
}

for dir in "${dirs[@]}"
do
	find "$dir" -name '*.scala' | while read f
	do
    check $f
  done
done

#!/usr/bin/env bash

help() {
	cat << EOF
Checks that all the comments at the beginning of the source files
have the correct file paths.

usage: $0 [-h|--help] [-v|--verbose] [dir ...]
where:
-h | --help     Print this message and exit
-v | --verbose  Print all files found, even those with no errors
dir ...         Start in these directories. (default "src")
EOF
}

verbose=false
dirs=()
while [[ $# -gt 0 ]]
do
	case $1 in
		-h|--h*)
			help
			exit 0
			;;
		-v|--v*)
			verbose=true
			;;
		-*)
			echo "ERROR: Unknown argument $1"
			help
			exit 1
			;;
		*)
			dirs+=($1)
			;;
	esac
	shift
done
[[ ${#dirs[@]} -gt 0 ]] || dirs=(src)


# Handle the case where some first lines are asciidoc
# directives like:
#   // BEGIN SERVICE
get_path() {
	head -3 "$@" | grep '// src' | sed -e 's?^// *??'
}

for dir in "${dirs[@]}"
do
	find "$dir" -name '*.scala' | while read f
	do
		comment=$(get_path $f)
		if [[ $comment != $f ]]
		then
			echo "$f =?= $comment   NOT EQUAL!"
		elif $verbose
		then
			echo "$f =?= $comment"
		fi
	done
done

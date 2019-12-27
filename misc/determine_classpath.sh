#!/usr/bin/env bash
#------------------------------------------------------------------------------
# Helper script used in the Makefile to "ask" SBT for the full CLASSPATH.
# Run it in the root directory of the project.
# It saves the found path in misc/classpath.txt, so SBT is called everytime.
# To force a recompute, delete that file, then run this script.
#------------------------------------------------------------------------------

get_paths() {
	sbt --no-colors "show fullClasspath" | \
	grep Attributed | \
	sed -e 's/^[^(]*(\([^)]*\)).*$/\1/'
}
make_classpath() {
	echo "${@} ." | sed -e 's/ /:/g'
}

if [[ -f misc/classpath.txt ]]
then
	cat misc/classpath.txt
else
	paths=( $(get_paths) )
	cp=$(make_classpath "${paths[@]}")
	echo $cp > misc/classpath.txt
	echo $cp
fi

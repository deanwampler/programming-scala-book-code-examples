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

cache=misc/classpath.txt
if [[ -f $cache ]]
then
	echo 1>&2
	echo "NOTE: Using $cache ($(ls -l $cache)). If it's old, delete and run again!!" 1>&2
	echo 1>&2
	cat $cache
else
	paths=( $(get_paths) )
	cp=$(make_classpath "${paths[@]}")
	echo $cp > $cache
	echo $cp
fi

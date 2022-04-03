#!/usr/bin/env zsh
# A script to run progscala3.meta.performance.InlinePerf program that looks at runtime performance when inline is used.
# See src/main/scala/progscala3/meta/performance/README.md for more details.
for warmup in true #false
do
	let n=1
	for i in {1..8}  # Used to generate n = 10 to 100,000,000
	do
		let "n = 10*$n"
		echo "## N = $n, warm up = $warmup"
		# TIP: Invoke this script with "NOOP=echo ./inline-perf.sh" and it will just echo the following line, rather than run it.
		$NOOP sbt "runMain progscala3.meta.performance.InlinePerf $warmup $n"
	done
done

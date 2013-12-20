#!/usr/bin/env bash
# code-examples/ToolsLibs/pipe-example.sh

h=Hello
w=World
function commands {
cat <<-EOF
println("$h")
println("$w")
EOF
}

commands | scala

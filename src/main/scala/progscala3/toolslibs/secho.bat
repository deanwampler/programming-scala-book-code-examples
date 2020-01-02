::#!
@echo off
call scala %0 %*
goto :eof
::!#
print("You entered: ")
argv.toList foreach { s => format("%s ", s) }
println

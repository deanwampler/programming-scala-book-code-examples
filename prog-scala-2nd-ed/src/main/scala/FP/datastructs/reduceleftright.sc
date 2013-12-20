// code-examples/FP/datastructs/reduceleftright-script.scala

println((1 to 1000000) reduceLeft(_ + _))
println((1 to 1000000) reduceRight(_ + _))

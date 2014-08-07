// src/main/scala/progscala2/fp/datastructs/reduceleftright.sc

println((1 to 1000000) reduceLeft(_ + _))
println((1 to 1000000) reduceRight(_ + _))

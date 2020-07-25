// src/script/scala-2/progscala3/bigdata/SparkWordCount.scala

val file = "README.md"
val input = sc.textFile(file).map(_.toLowerCase)                 // <1>
input
  .flatMap(line => line.split("""\W+"""))                        // <2>
  .map(word => (word, 1))                                        // <3>
  .reduceByKey((count1, count2) => count1 + count2)              // <4>
  .saveAsTextFile(file+".wordcount")                             // <5>

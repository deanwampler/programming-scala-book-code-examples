// src/script/scala-2/progscala3/bigdata/SparkWordCount.scala

val file = "README.md"                                           // <1>
val input = sc.textFile(file).map(_.toLowerCase)                 // <2>
input
  .flatMap(line => line.split("""\W+"""))                        // <3>
  .map(word => (word, 1))                                        // <4>
  .reduceByKey((count1, count2) => count1 + count2)              // <5>
  .saveAsTextFile(file+".wordcount")                             // <6>

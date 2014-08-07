// src/main/scala/progscala2/patternmatching/match-deep-tuple.sc

val itemsCosts = Seq(("Pencil", 0.52), ("Paper", 1.35), ("Notebook", 2.43))
val itemsCostsIndices = itemsCosts.zipWithIndex
for (itemCostIndex <- itemsCostsIndices) { 
  itemCostIndex match {
    case ((item, cost), index) => println(s"$index: $item costs $cost each")
  }
}

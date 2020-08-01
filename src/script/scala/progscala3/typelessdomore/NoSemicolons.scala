// src/script/scala/progscala3/typelessdomore/NoSemicolons.scala

def equalsign(s: String) =                       // <1>
  println("equalsign: " + s)

def equalsign2(s: String) = {                    // <2>
  println("equalsign2: " + s)
}

def commas(s1: String,                           // <3>
           s2: String) = Console.
  println("comma: " + s1 +
          ", " + s2)

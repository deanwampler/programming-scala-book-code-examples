// src/script/scala/progscala3/typelessdomore/CountTo.scala

def countTo(n: Int): Unit = {
  def count(i: Int): Unit = {
    if (i <= n) then
    	if (i <= n) then
    		println(i)
    		count(i + 1)
  }
  count(1)
}

countTo(5)

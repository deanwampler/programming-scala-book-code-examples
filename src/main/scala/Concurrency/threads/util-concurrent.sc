// src/main/scala/Concurrency/threads/util-concurrent.sc

import java.util.concurrent._

class ThreadIdentifier extends Runnable {
  def run {
    println("hello from Thread " + currentThread.getId)
  }
}

val pool = Executors.newFixedThreadPool(5)

for (i <- 1 to 10) {
  pool.execute(new ThreadIdentifier)
}

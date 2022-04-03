// src/main/scala/progscala3/meta/performance/InlinePerf.scala
// This code does not appear in the book.
package progscala3.meta.performance
import scala.quoted.*
import scala.math.random

@main def InlinePerf(warmup: Boolean, numberOfTrials: Int*) =

  class Thing(var state: Double, var count: Long = 0L, var label: String):
    def update(): Unit = 
      state = random()
      count += 1

  def trialBaseline(n: Int): Long =
    val startBaseline = System.nanoTime()
    val thing = new Thing(0.0, 0L, "baseline")
    for i <- 1 to n do thing.update()
    val endBaseline = System.nanoTime()
    assert(thing.count == n)  // Sanity check: thing.update was called and not optimized away.
    endBaseline - startBaseline

  def trialEnabled(n: Int): Long =
    val startEnabled = System.nanoTime()
    val thing = new Thing(0.0, 0L, "enabled")
    for i <- 1 to n
    do
      invariantEnabled(thing.label == "enabled") {
        thing.update()
      }
    val endEnabled = System.nanoTime()
    assert(thing.count == n)  // Sanity check: thing.update was called and not optimized away.
    endEnabled - startEnabled

  def trialDisabled(n: Int): Long =
    val startDisabled = System.nanoTime()
    val thing = new Thing(0.0, 0L, "disabled")
    for i <- 1 to n
    do
      invariantDisabled(thing.label == "disabled") {
        thing.update()
      }
    val endDisabled = System.nanoTime()
    assert(thing.count == n)  // Sanity check: thing.update was called and not optimized away.
    endDisabled - startDisabled

  def trialMinInline(n: Int): Long =
    val startMinInline = System.nanoTime()
    val thing = new Thing(0.0, 0L, "min-inline")
    for i <- 1 to n
    do
      invariantMinInline(thing.label == "min-inline") {
        thing.update()
      }
    val endMinInline = System.nanoTime()
    assert(thing.count == n)  // Sanity check: thing.update was called and not optimized away.
    endMinInline - startMinInline

  def separator() =
    println( "|+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++|")

  def header() =
    separator()
    println(f"|         N |                      Elapsed Times (nanoseconds), JVM Warmup? $warmup%5s                                       |")
    println( "|           |   Baseline |    Enabled |   Disabled |     E/B% |     D/B% |     E/D% |   MinInline |     M/B% |     M/E% |")
    separator()

  header()
  val ns = if numberOfTrials.size == 0 then Seq(1000000) else numberOfTrials
  // Warm up the JVM with 1000000 runs.
  // The warmup causes DRAMATICALLY different results!
  if warmup then
    trialBaseline(1000000)
    trialMinInline(1000000)
    trialEnabled(1000000)
    trialDisabled(1000000)

  for n <- numberOfTrials 
  do 
    val timeBaseline  = trialBaseline(n)
    val timeMinInline = trialMinInline(n)
    val timeEnabled   = trialEnabled(n)
    val timeDisabled  = trialDisabled(n)
    val percentEB = (100.0 * timeEnabled)/timeBaseline
    val percentDB = (100.0 * timeDisabled)/timeBaseline
    val percentED = (100.0 * timeEnabled)/timeDisabled
    val percentMB = (100.0 * timeMinInline)/timeBaseline
    val percentME = (100.0 * timeMinInline)/timeEnabled
    println(f"| $n%9d | $timeBaseline%10d | $timeEnabled%10d | $timeDisabled%10d " +
            f"| $percentEB%7.2f%% | $percentDB%7.2f%% | $percentED%7.2f%% " +
            f"|  $timeMinInline%10d | $percentMB%7.2f%% | $percentME%7.2f%% |")
  separator()


# README for package progscala3.meta.performance

This code is not discussed in the book, but an early version of it was discussed in [this blog post](https://medium.com/scala-3/scala-3-a-look-at-inline-and-programming-scala-is-now-published-9690ca43c23a). It experiments with the performance benefits of inlining vs. not inlining. 

> **Disclaimer:** There are lies, damn lies, and performance benchmarks. As discussed below, a lot is going on in JVM-based software, causing highly nontrivial behavior. At the end of the day, what really matters is how well your whole application performs in real deployment scenarios, rather than how a microbenchmark performs. The results here are measured with a single, ad-hoc example, running on an "elderly" iMac with other processes running. Your mileage/kilometerage will vary.

With that understanding... the `Invariant*.scala` files are copies of `../Invarint.scala` with various changes to enable or disable the invariant logic. I also experimented with using `inline` less aggressively:

* `InvariantDisabled.scala`: The same as `../Invariant.scala` with `inline val ignore = true` instead of `false`.
* `InvariantEnabled.scala`: Identical to `../Invariant.scala`.
* `InvariantMinInline.scala`: Similar to `../Invariant.scala`, but most `inline` keywords are removed. The ones that remain are necessary for the macro expansions to work.

(I'm ignoring trivial differences with `../Invariant.scala`, including the different package and object names and comment differences.)

`InlinePerf.scala` is a "driver" program that tries the various configurations and reports runtime performance numbers, where `N` is the number of trials, i.e., how many passes through a for loop. 

The following class definition is used in the test:

```scala
  class Thing(var state: Double, var count: Long = 0L, var label: String):
    def update(): Unit = 
      state = random()
      count += 1
```

Here is a typical invariant check done with `invariantEnabled`:

```scala
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
    endEnabled - startEnabled // What is reported in the tables below for each N.
```

There is also a `trialDisabled` method that uses `invariantDisabled`, which is idential to `invariantEnabled`, but the check is turned off.

There is also a _Baseline_ measurement without the invariant logic:

```scala
  def trialBaseline(n: Int): Long =
    val startBaseline = System.nanoTime()
    val thing = new Thing(0.0, 0L, "baseline")
    for i <- 1 to n do thing.update()
    val endBaseline = System.nanoTime()
    assert(thing.count == n)  // Sanity check: thing.update was called and not optimized away.
    endBaseline - startBaseline

```

The program takes one or more arguments. The first argument is required and it must be `true` or `false`, indicating whether or not to warm up the JVM by running some of the code before test measurements. _This makes an enormous difference_, which is the first indication of how tricky it is to understand what's going on.

The subsequent arguments are integers for the number of trials to run for averaging purposes. If none is specified, the default is 1,000,000. This feature is less useful than it might appear, again because earlier runs warm up the JVM so comparing their results with later results in the same invocation is suspect, because they aren't "clean".

For that reason, the tables below started `sbt` and passed two arguments each time, either `true` or `false` and a number for the trials, 10 to 100,000,000, so a fresh start was made each time. The shell script `inline-perf.sh`, which is in the root directory of the repo, was used to compute the results below.

As an example, the very last row of Table 1 was output from this command: `sbt "runMain progscala3.meta.performance.InlinePerf true 100000000"`.

So, here is the output of `inline-perf.sh` condensed into two tables of results:


Table 1: JVM Warmup. Elapsed Times in nanoseconds.

|         N |   Baseline |    Enabled |   Disabled |     E/B% |     D/B% |     E/D% |  MinInline |     M/B% |     M/E% |
| --------: | ---------: | ---------: | ---------: | -------: | -------: | -------: | ---------: | -------: | -------: |
|        10 |      10749 |      24073 |      20103 |  223.96% |  187.02% |  119.75% |      34847 |  324.19% |  144.76% |
|       100 |      12703 |      64740 |      62045 |  509.64% |  488.43% |  104.34% |      91273 |  718.52% |  140.98% |
|      1000 |      31496 |      65560 |     111874 |  208.15% |  355.20% |   58.60% |     121797 |  386.71% |  185.78% |
|     10000 |     236508 |     647320 |     638182 |  273.70% |  269.84% |  101.43% |     745498 |  315.21% |  115.17% |
|    100000 |    2075219 |    4517619 |    2671433 |  217.69% |  128.73% |  169.11% |    3278139 |  157.97% |   72.56% |
|   1000000 |   21011322 |   25935057 |   27546366 |  123.43% |  131.10% |   94.15% |   30261666 |  144.03% |  116.68% |
|  10000000 |  213610827 |  324775224 |  254720329 |  152.04% |  119.25% |  127.50% |  312444240 |  146.27% |   96.20% |
| 100000000 | 2117243309 | 2658438580 | 2582046806 |  125.56% |  121.95% |  102.96% | 2737337076 |  129.29% |  102.97% |


Table 2: No JVM Warmup. Elapsed Times in nanoseconds.

|         N |   Baseline |    Enabled |   Disabled |     E/B% |     D/B% |     E/D% |  MinInline |     M/B% |     M/E% |
| --------: | ---------: | ---------: | ---------: | -------: | -------: | -------: | ---------: | -------: | -------: |
|        10 |    3088053 |     477663 |     412940 |   15.47% |   13.37% |  115.67% |    1457035 |   47.18% |  305.03% |
|       100 |    3789123 |     673191 |     552603 |   17.77% |   14.58% |  121.82% |    2005214 |   52.92% |  297.87% |
|      1000 |    5324456 |     998170 |     807535 |   18.75% |   15.17% |  123.61% |    2790328 |   52.41% |  279.54% |
|     10000 |    7134938 |    3057802 |    1931022 |   42.86% |   27.06% |  158.35% |    5968999 |   83.66% |  195.21% |
|    100000 |   13016288 |    9854473 |    4979066 |   75.71% |   38.25% |  197.92% |   10051488 |   77.22% |  102.00% |
|   1000000 |   37061517 |   34398294 |   26770855 |   92.81% |   72.23% |  128.49% |   33458185 |   90.28% |   97.27% |
|  10000000 |  230187564 |  291201716 |  319657062 |  126.51% |  138.87% |   91.10% |  223450022 |   97.07% |   76.73% |
| 100000000 | 2090342053 | 2484718677 | 2381468270 |  118.87% |  113.93% |  104.34% | 2150088896 |  102.86% |   86.53% |


For reference, these numbers were measured on a 9-year-old iMac, with a 3.4 GHz Quad-Core Intel Core i7 CPU and 23GB Ram. I used JDK 17 and Scala 3.1.0. The elapsed time numbers can vary quite a lot from run to run, especially for smaller `N`. 

Let's look at the numbers.  

First, consider the difference that JVM warmup makes in Table 1. In this case, each calculation was performed 1M times and then the for loops for the given `N` were timed. Note that for `N` up to 100, the time growth doesn't scale 10x between rows because of other overhead. The numbers shown are ~10 _microseconds_. Only for `N` >= 1000 does the approximate execution time scale roughly 10x. In Table 2, without JVM warm up, 10x scaling doesn't really start until roughly `N` = 1M.

Focusing again on the JVM warmup numbers in Table 1, the overhead of enabling the invariant checks over the baseline is roughly 2x at smaller `N` (ignoring the probable outlier for `N` = 100), but it appears to trend down towards 1.2x for very large `N`. This overhead could be perfectly acceptable except for the most extreme, performance-sensitive situations. Comparing the differences between enabled and disabled cases, there is a lot of noise in the data, but it is reasonable to infer that the overhead is roughly the same for the macro, enabled or not. This may reflect the fact that the actual check used for the invariant example, where a string value should not change, is very fast to check.

Comparing the results in the two tables, we see that for small `N` warmup makes a significant difference, but for large `N`, the results are similar. This is what we would expect, since for large `N` we are _effectively_ doing JVM warmup anyway.

The _MinInline_ case was added because aggressive inlining can backfire if it causes significant code bloat. While calling a function has overhead, there is a tipping point where this overhead is better than inlining its definition in so many places that the code becomes bloated. Unrolling a loop can be another potential example. Code bloat has been a well-known issue with C++ template expansion for a long time. 

So, if more aggressive inlining is beneficial, we would expect the `M/E%` column to be greater than 100%. For large `N` it appears to make little difference. For smaller `N`, the more aggressive inlining does reduce overhead. Similarly, comparing `M/B%` vs. `E/B%` shows smaller overhead for aggressive inlining. So, the aggressively-inlined version is better, at least for this example, although less so for large `N`.

Why would aggressive inlining be beneficial? One likely benefit is the elimination of the conditional check:

```scala
    inline if !ignore then
      if !predicate then fail(predicate, message, block, "before")
      val result = block
      if !predicate then fail(predicate, message, block, "after")
      result
    else
      block
```

As discussed in the book, when `ignore` is `true`, the byte code only contains `block`. However, I'm a little surprised that the numbers for `D/B%` aren't closer to 100%, meaning the byte code for the disabled case is nearly identical to the baseline case. I haven't looked at the generated byte code for these two cases, but it appears they are different.


I put in numbers for small `N` to try to see the overhead in a more granular way, especially when there is no JVM warmup, but in fact these numbers are hard to intrepret, especially for the no-warmup case. They don't scale 10x from row to row, indicating that significant background overhead is in play. 

## Conclusions

So what conclusions can we draw? For an invariant check that is performed rarely, i.e., low `N`, we probably don't care about the overhead anyway. For large `N`, the overhead of the code appears to be converging around 20%. This may also be perfectly acceptable in most cases.

The same arguments apply to inlining. There will certainly be cases where inlining results in noticeable savings, but it's not obviously true for all code constructs. The savings benefit, if any, will also depend on how frequently the code is called.

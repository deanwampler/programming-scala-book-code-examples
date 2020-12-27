// src/script/scala/progscala3/typelessdomore/Ranges.scala

1 to 10                   // Int range inclusive, interval of 1, (1 to 10)

1 until 10                // Int range exclusive, interval of 1, (1 to 9)

1 to 10 by 3              // Int range inclusive, every third.

1L to 10L by 3            // Long values

'a' to 'g' by 3           // Char values

// Not shown in the book, so I won't need to explain why back ticks are used
// here. The methods "to" and "by" are not declared infix or otherwise allowed
// to be infix, as of Scala 3.0.0-M3. This will probably be fixed by 3.0 final.
BigInt(1) `to` BigInt(10) `by` 3

BigDecimal(1.1) `to` BigDecimal(10.3) `by` 3.1




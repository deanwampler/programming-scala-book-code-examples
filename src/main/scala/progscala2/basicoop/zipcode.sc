// src/main/scala/progscala2/basicoop/Zipcode.sc
import progscala2.basicoop.ZipCode

ZipCode(12345)
// Result: ZipCode = 12345

ZipCode(12345, Some(6789))
// Result: ZipCode = 12345-6789

ZipCode(12345, 6789)
// Result: ZipCode = 12345-6789

try {
  ZipCode(0, 6789)  // Invalid Zip+4 specified: 0-6789
} catch {
  case e: java.lang.IllegalArgumentException => e
}

try {
  ZipCode(12345, 0)  // Invalid Zip+4 specified: 12345-0
} catch {
  case e: java.lang.IllegalArgumentException => e
}

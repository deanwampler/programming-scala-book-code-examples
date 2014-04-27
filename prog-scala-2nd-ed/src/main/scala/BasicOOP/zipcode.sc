// src/main/scala/BasicOOP/zipcode.sc

import oop.ZipCode

ZipCode(12345)
// Result: ZipCode = 12345

ZipCode(12345, Some(6789))
// Result: ZipCode = 12345-6789

ZipCode(12345, 6789)
// Result: ZipCode = 12345-6789

ZipCode(0, 6789)
// Result: java.lang.IllegalArgumentException: requirement failed: 
//   Invalid Zip+4 specified: 0-6789

ZipCode(12345, 0)
// Result: java.lang.IllegalArgumentException: requirement failed: 
//   Invalid Zip+4 specified: 12345-0

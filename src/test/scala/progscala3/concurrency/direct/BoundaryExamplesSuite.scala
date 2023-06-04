// src/test/scala/progscala3/concurrency/direct/BoundaryExamplesSuite.scala
package progscala3.concurrency.boundary

import munit.ScalaCheckSuite
import org.scalacheck.*

class BoundaryExamplesSuite extends ScalaCheckSuite:
  import Prop.forAll

  property("BoundaryExamples.firstIndex returns an index between 0 and the sequence size minus one if the element exists") {
    forAll(Gen.choose(1, 10)) { case size: Int =>
      val top = size - 1
      val xs = (0 until size).toList
      xs.forall(i => BoundaryExamples.firstIndex(xs, i) == i)
    }
  }

  property("BoundaryExamples.firstIndex returns -1 if the element does not exist") {
    forAll(Gen.choose(1, 10)) { case size: Int =>
      val xs = (0 until size).toList
      BoundaryExamples.firstIndex(xs, -1)   == -1 &&
      BoundaryExamples.firstIndex(xs, size) == -1
    }
  }

  property("BoundaryExamples.firstTwoIndices returns indices between 0 and the sequence size minus one if the elements exist") {
    forAll(Gen.choose(1, 10)) { case size: Int =>
      val top = size - 1
      val xs = (0 until size).toList
      xs.combinations(2).forall { (iter: List[Int]) => 
        val i = iter(0)
        val j = iter(1)
        BoundaryExamples.firstTwoIndices(xs, i,  j) == (i, j)
      }
    }
  }

  property("BoundaryExamples.firstTwoIndices returns (-1, -1) if the first element does not exist") {
    forAll(Gen.choose(1, 10)) { case size: Int =>
      val xs = (0 until size).toList
      xs.forall { i => 
        BoundaryExamples.firstTwoIndices(xs,   -1, i) == (-1, -1) &&
        BoundaryExamples.firstTwoIndices(xs, size, i) == (-1, -1)
      }
    }
  }

  property("BoundaryExamples.firstTwoIndices returns (n, -1) if the second element does not exist, but the first element is found at index n") {
    forAll(Gen.choose(1, 10)) { case size: Int =>
      val xs = (0 until size).toList
      xs.forall { i => 
        BoundaryExamples.firstTwoIndices(xs, i,   -1) == (i, -1) &&
        BoundaryExamples.firstTwoIndices(xs, i, size) == (i, -1)
      }
    }
  }

  property("BoundaryExamples.firstColumn returns Some(List(...)) with the first elements if every input 'column' has at least one element") {
    forAll(Gen.choose(1, 10)) { case size: Int =>
      val xsSome  = (0 to size).toList
      val xssSome = xsSome.map(i => (i to 0 by -1).toList)
      BoundaryExamples.firstColumn(xssSome) == Some(xsSome)
    }
  }

  property("BoundaryExamples.firstColumn returns None if any input 'column' is empty") {
    forAll(Gen.choose(1, 10)) { case size: Int =>
      val xsSome  = (0 to size).toList
      val xssSome = xsSome.map(i => (i to 0 by -1).toList)
      val xssNone = xsSome.map(i => xssSome.updated(i, List.empty[Int]))
      xssNone.forall { xss => 
        BoundaryExamples.firstColumn(xss) == None
      }
    }
  }

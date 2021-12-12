package aoc

import org.scalatest.funsuite.AnyFunSuite

class Test12 extends AnyFunSuite {
  test("basic example") {
    val input =
      """start-A
        |start-b
        |A-c
        |A-b
        |b-d
        |A-end
        |b-end
        |""".stripMargin
    val expectedPaths = 
      """start,A,b,A,c,A,end
        |start,A,b,A,end
        |start,A,b,end
        |start,A,c,A,b,A,end
        |start,A,c,A,b,end
        |start,A,c,A,end
        |start,A,end
        |start,b,A,c,A,end
        |start,b,A,end
        |start,b,end""".stripMargin
      .split("\n")
      .map(_.split(",").toSeq)
      .toSet
    assert(solve(input) == expectedPaths)
  }
}

package aoc

import org.scalatest.Assertion
import org.scalatest.funsuite.AnyFunSuite

class Test12 extends AnyFunSuite {
  test("1: basic example") {
    testSolve1(
      """start-A
        |start-b
        |A-c
        |A-b
        |b-d
        |A-end
        |b-end
        |""".stripMargin,
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
    )
  }

  test("1: larger example") {
    testSolve1(
      """dc-end
        |HN-start
        |start-kj
        |dc-start
        |dc-HN
        |LN-dc
        |HN-end
        |kj-sa
        |kj-HN
        |kj-dc""".stripMargin,
      """start,HN,dc,HN,end
        |start,HN,dc,HN,kj,HN,end
        |start,HN,dc,end
        |start,HN,dc,kj,HN,end
        |start,HN,end
        |start,HN,kj,HN,dc,HN,end
        |start,HN,kj,HN,dc,end
        |start,HN,kj,HN,end
        |start,HN,kj,dc,HN,end
        |start,HN,kj,dc,end
        |start,dc,HN,end
        |start,dc,HN,kj,HN,end
        |start,dc,end
        |start,dc,kj,HN,end
        |start,kj,HN,dc,HN,end
        |start,kj,HN,dc,end
        |start,kj,HN,end
        |start,kj,dc,HN,end
        |start,kj,dc,end""".stripMargin
    )
  }

  test("1: test example") {
    val solution = solve1(
      """mx-IQ
        |mx-HO
        |xq-start
        |start-HO
        |IE-qc
        |HO-end
        |oz-xq
        |HO-ni
        |ni-oz
        |ni-MU
        |sa-IE
        |IE-ni
        |end-sa
        |oz-sa
        |MU-start
        |MU-sa
        |oz-IE
        |HO-xq
        |MU-xq
        |IE-end
        |MU-mx""".stripMargin
    )
    println(solution.size)
  }

  test("2: basic example") {
    testSolve2(
      """start-A
        |start-b
        |A-c
        |A-b
        |b-d
        |A-end
        |b-end
        |""".stripMargin,
      """start,A,b,A,b,A,c,A,end
        |start,A,b,A,b,A,end
        |start,A,b,A,b,end
        |start,A,b,A,c,A,b,A,end
        |start,A,b,A,c,A,b,end
        |start,A,b,A,c,A,c,A,end
        |start,A,b,A,c,A,end
        |start,A,b,A,end
        |start,A,b,d,b,A,c,A,end
        |start,A,b,d,b,A,end
        |start,A,b,d,b,end
        |start,A,b,end
        |start,A,c,A,b,A,b,A,end
        |start,A,c,A,b,A,b,end
        |start,A,c,A,b,A,c,A,end
        |start,A,c,A,b,A,end
        |start,A,c,A,b,d,b,A,end
        |start,A,c,A,b,d,b,end
        |start,A,c,A,b,end
        |start,A,c,A,c,A,b,A,end
        |start,A,c,A,c,A,b,end
        |start,A,c,A,c,A,end
        |start,A,c,A,end
        |start,A,end
        |start,b,A,b,A,c,A,end
        |start,b,A,b,A,end
        |start,b,A,b,end
        |start,b,A,c,A,b,A,end
        |start,b,A,c,A,b,end
        |start,b,A,c,A,c,A,end
        |start,b,A,c,A,end
        |start,b,A,end
        |start,b,d,b,A,c,A,end
        |start,b,d,b,A,end
        |start,b,d,b,end
        |start,b,end""".stripMargin
    )
  }

  test("2: test example") {
    val solution = solve2(
      """mx-IQ
        |mx-HO
        |xq-start
        |start-HO
        |IE-qc
        |HO-end
        |oz-xq
        |HO-ni
        |ni-oz
        |ni-MU
        |sa-IE
        |IE-ni
        |end-sa
        |oz-sa
        |MU-start
        |MU-sa
        |oz-IE
        |HO-xq
        |MU-xq
        |IE-end
        |MU-mx""".stripMargin
    )
    println(solution.size)
  }

  private val testSolve1 = testSolve(solve1)

  private val testSolve2 = testSolve(solve2)

  private def testSolve(solve: String => Set[Path])(input: String, expectedOutput: String): Assertion = {
    val expectedPaths = expectedOutput
      .split("\n")
      .map(_.split(",").toSeq)
      .toSet
    assert(solve(input) == expectedPaths)
  }
}

package aoc

import java.io.InputStream

type Node = String
type Path = Seq[Node]
type Edge = (Node, Node)

def solve1(input: String): Set[Path] = solve1(index(parse(input)))

private def solve1(edges: Map[Node, Set[Node]]): Set[Path] =
  extend1(edges, Set(Seq("start")))

private def extend1(edges: Map[Node, Set[Node]], paths: Set[Path]): Set[Path] =
  val (complete, toExtend) = paths.partition(_.endsWith(Seq("end")))
  val extended = toExtend.flatMap(step1(edges, _))
  if (extended.isEmpty) complete else complete ++ extend1(edges, extended)

private def step1(edges: Map[Node, Set[Node]], path: Path): Set[Path] = edges(path.last)
  .filter { nextNode => isBig(nextNode) || !path.contains(nextNode) }
  .map(path :+ _)

def solve2(input: String): Set[Path] = solve2(index(parse(input)))

private def solve2(edges: Map[Node, Set[Node]]): Set[Path] =
  extend2(edges, Set(Seq("start")))

private def extend2(edges: Map[Node, Set[Node]], paths: Set[Path]): Set[Path] =
  val (complete, toExtend) = paths.partition(_.endsWith(Seq("end")))
  val extended = toExtend.flatMap(step2(edges, _))
  if (extended.isEmpty) complete else complete ++ extend2(edges, extended)

private def step2(edges: Map[Node, Set[Node]], path: Path): Set[Path] = edges(path.last)
  .filter { nextNode =>
    nextNode != "start" && (
      isBig(nextNode) ||
        !path.contains(nextNode) ||
        !containsMultipleSmall(path)
      )
  }
  .map(path :+ _)

private def parse(input: String): Set[Edge] = input
  .split("\n")
  .flatMap { line =>
    val Array(n1, n2) = line.split("-")
    Set((n1, n2), (n2, n1))
  }
  .toSet

private def index(edges: Set[Edge]): Map[Node, Set[Node]] = edges
  .groupBy(_._1)
  .view
  .mapValues(_.map(_._2))
  .toMap

private def isBig(node: Node): Boolean = node.toUpperCase == node

private def containsMultipleSmall(path: Path): Boolean = path
  .filterNot(isBig)
  .groupBy(identity)
  .values
  .map(_.size)
  .exists(_ > 1)

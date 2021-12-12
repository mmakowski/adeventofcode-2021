package aoc

import java.io.InputStream

type Node = String
type Path = Seq[Node]

def solve(input: String): Set[Path] = solve(index(parse(input)))

type Edge = (Node, Node)

private def solve(edges: Map[Node, Set[Node]]): Set[Path] =
  val paths = Set(Seq("start"))
  extend(edges, paths)

private def extend(edges: Map[Node, Set[Node]], paths: Set[Path]): Set[Path] =
  val (complete, toExtend) = paths.partition(_.endsWith(Seq("end")))
  val extended = toExtend.flatMap(step(edges, _))
  if (extended.isEmpty) complete else complete ++ extend(edges, extended)

private def step(edges: Map[Node, Set[Node]], path: Path): Set[Path] = edges(path.last)
  .filter { nextNode => isBig(nextNode) || !path.contains(nextNode) }
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
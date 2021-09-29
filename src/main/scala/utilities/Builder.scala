package utilities

import toolset.{Grammar, NonTerminal, Production, Terminal}

import scala.io.Source

object Builder {
  def asCFG(source: Source): Grammar = try {
    val productions = source.getLines()
      .map(line => line.split("->"))
      .map(partition => (partition(0), partition(1)))
      .map(partitions => {
        val (left, right) = partitions
        Production(
          NonTerminal(left.trim),
          right.trim.split("\\s").map(_.trim).map(t => {
            if (t(0).isUpper)
              NonTerminal(t)
            else
              Terminal(t)
          }).toSeq
        )
      }).toSeq

    Grammar(productions)
  } finally {
    source.close()
  }
}

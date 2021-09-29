package toolset

object Predict {
  def apply(implicit grammar: Grammar): Map[NonTerminal, Set[Terminal]] = grammar.productions.toSet.groupBy(x => x.lhs)
    .map(x => x._1 -> x._2.flatMap(y => BlackDot(First(Set())(y.rhs), Follow(Set())(x._1))))
    .toMap
}

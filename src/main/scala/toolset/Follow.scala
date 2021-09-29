package toolset

object Follow {
  def apply(visited: Set[Production])(variable: Variable)(implicit grammar: Grammar): Set[Terminal] = if (variable.isInstanceOf[Terminal])
    Set()
  else
    (grammar.productions
      .filter(p => p.rhs.contains(variable)).toSet -- visited)
      .flatMap(p => BlackDot(
        First(Set())(p.rhs.drop(p.rhs.indexOf(variable))),
        Follow(visited + p)(p.lhs)
      ))
      .toSet

  def apply(implicit grammar: Grammar): Map[NonTerminal, Set[Terminal]] =
    grammar.productions.map(_.lhs).toSet.map(x => x -> Follow(Set())(x)).toMap
}

package toolset

object First {
  def apply(visited: Set[Production])(variables: Seq[Variable])(implicit grammar: Grammar): Set[Terminal] =
    if (variables.isEmpty)
      Set()
    else
      BlackDot(First(visited)(variables(0)), First(visited)(variables.drop(1)))

  private def apply(visited: Set[Production])(variable: Variable)(implicit grammar: Grammar): Set[Terminal] = if (variable.isInstanceOf[Terminal])
    Set(variable.asInstanceOf[Terminal])
  else
    val nonTerminal = variable.asInstanceOf[NonTerminal]
    (grammar.productions
      .filter(x => x.lhs == nonTerminal).toSet -- visited)
      .flatMap(p => First(visited + p)(p.rhs))
      .toSet

  def apply(implicit grammar: Grammar): Map[NonTerminal, Set[Terminal]] =
    grammar.productions.map(_.lhs).toSet.map(x => x -> First(Set())(x)).toMap
}

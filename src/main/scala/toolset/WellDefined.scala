package toolset

object WellDefined {

  private def apply(visited: Set[Production])(production: Production)(implicit grammar: Grammar): Boolean = {
    val isSimple = production.rhs.forall(v => v.isInstanceOf[Terminal])
    if (isSimple) true
    else
      (production.rhs
        .filter(x => x.isInstanceOf[NonTerminal])
        .map(x => x.asInstanceOf[NonTerminal])
        .flatMap(nonTerminal => grammar.productions.filter(p => p.lhs == nonTerminal)).toSet -- visited)
        .exists(x => apply(visited + production)(x))
  }

  def apply(implicit grammar: Grammar): Boolean =
    grammar.productions.forall(p => apply(Set())(p))
}

package toolset

object Refactor {
  def apply(grammar: Grammar): Grammar = Grammar(
    grammar.productions
      .flatMap(production =>
        if (production.rhs.contains(production.lhs))
          Seq(
            Production(production.lhs, production.rhs.drop(1) :+ production.lhs),
            Production(production.lhs, Seq(Epsilon))
          ) else
          Seq(production))
  )
}

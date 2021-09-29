package toolset


trait Variable

case class Terminal(name: String) extends Variable
case class NonTerminal(name: String) extends Variable
case class Production(lhs: NonTerminal, rhs: Seq[Variable])
case class Grammar(productions: Seq[Production])

object Epsilon extends Terminal("epsilon")
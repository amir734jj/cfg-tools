package toolset

object Tools {

  extension (grammar: Grammar)
    def leftRefactor: Grammar = Refactor(grammar)

    def first: Map[NonTerminal, Set[Terminal]] = First(grammar)

    def follow: Map[NonTerminal, Set[Terminal]] = Follow(grammar)

    def predict: Map[NonTerminal, Set[Terminal]] = Predict(grammar)

    def isWellDefined: Boolean = WellDefined(grammar)
}

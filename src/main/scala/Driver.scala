import utilities.Builder.asCFG
import toolset.Tools._

import scala.io.Source

object Driver {
  @main def main() = {
    val source = Source.fromResource("grammar.txt")
    val grammar = asCFG(source)
    println(grammar)
    println(grammar.leftRefactor)
    println(grammar.isWellDefined)
    println(grammar.first)
    println(grammar.follow)
    println(grammar.predict)
  }
}

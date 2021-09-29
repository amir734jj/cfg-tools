package toolset

object BlackDot {
  def apply(set1: Set[Terminal], set2: Set[Terminal]): Set[Terminal] = if (set1.contains(Epsilon))
    (set1 - Epsilon) ++ set2
  else
    set1
}

import kotlin.math.max
import kotlin.math.min

fun probabilityOfMatch(searchTerms: List<String>, content: String): Result {
    val contentArray = content.lowercase().split(' ')
    var closestMatch: Pair<String?, String?>? = null
    var bestSimilarity = 0f

    contentArray.forEach { word ->
        searchTerms.map { it.lowercase() }.forEach { term ->
            val distance = levenshteinDistance(term, word)
            val largest = max(term.length, word.length)
            val similarity = ((largest - distance) / largest.toFloat()) * 100f
            if (similarity > bestSimilarity) {
                bestSimilarity = similarity
                closestMatch = Pair(term, word)
            }
        }
    }

    return Result(bestSimilarity, closestMatch?.first, closestMatch?.second)
}

private fun levenshteinDistance(lhs : CharSequence, rhs : CharSequence) : Int {
    if(lhs == rhs) { return 0 }
    if(lhs.isEmpty()) { return rhs.length }
    if(rhs.isEmpty()) { return lhs.length }

    val lhsLength = lhs.length + 1
    val rhsLength = rhs.length + 1

    var cost = Array(lhsLength) { it }
    var newCost = Array(lhsLength) { 0 }

    for (i in 1 until rhsLength) {
        newCost[0] = i

        for (j in 1 until lhsLength) {
            val match = if(lhs[j - 1] == rhs[i - 1]) 0 else 1

            val costReplace = cost[j - 1] + match
            val costInsert = cost[j] + 1
            val costDelete = newCost[j - 1] + 1

            newCost[j] = min(min(costInsert, costDelete), costReplace)
        }

        val swap = cost
        cost = newCost
        newCost = swap
    }

    return cost[lhsLength - 1]
}
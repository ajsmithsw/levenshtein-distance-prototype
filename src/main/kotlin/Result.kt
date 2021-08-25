data class Result(
        val similarity: Float,
        val closestSearchTerm: String?,
        val closestContentMatch: String?) {
    override fun toString(): String {
        return "accuracy: ${"%.2f".format(similarity)}% | best match:  term:\"$closestSearchTerm\" : word:\"$closestContentMatch"
    }
}

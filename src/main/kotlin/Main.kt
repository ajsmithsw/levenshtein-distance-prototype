
// Edit these as much as you like
val searchTerms = listOf("bday", "bd", "tim")
const val content = "It's about time someone had a birthday around here"
const val threshold = 0.5f

fun main(args: Array<String>) {

    val result = probabilityOfMatch(searchTerms, content)

    println(if (result.similarity >= threshold) "POSSIBLE MATCH FOUND" else "NO MATCH FOUND")
    println("\nsearch terms: $searchTerms\ncontent: $content\nresult: $result")
}
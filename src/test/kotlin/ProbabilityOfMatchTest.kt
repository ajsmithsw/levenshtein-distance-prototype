import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream
import kotlin.test.assertEquals

class ProbabilityOfMatchTest {

    @ParameterizedTest
    @MethodSource("provideTestParams")
    fun `test match probability`(searchTerms: List<String>, content: String, similarity: Float) {
        val result = probabilityOfMatch(searchTerms, content)
        assertEquals(similarity, result.similarity)
    }

    companion object {
        @JvmStatic
        fun provideTestParams(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(listOf("birthday"), "My Birthday", 100f),
                Arguments.of(listOf("birthday"), "My Bday", 50f),
                Arguments.of(listOf("birthday", "bday", "birth"), "My Bday", 100f),
                Arguments.of(listOf("birthday", "bday", "birth"), "My Birthday", 100f),
                Arguments.of(listOf("peacocks"), "I love peas", 50f),
                Arguments.of(listOf("razzmatazz"), "tazzmatazz", 90f),
                Arguments.of(listOf("people"), "object", 0f)
            )
        }
    }
}
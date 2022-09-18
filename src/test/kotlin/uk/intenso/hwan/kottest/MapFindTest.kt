package uk.intenso.hwan.kottest

import org.junit.jupiter.api.*
import org.assertj.core.api.Assertions.*

internal class MapFindTest {

        private val mapFinder = MapFind()
        private var testMap: AnyMap? = null;

        @BeforeEach
        fun setup() {
            val stringListMap = mapOf(
                Pair("Greetings", listOf("Hello", "World")),
                Pair("Groceries", mapOf(Pair("Fruits",listOf("Apple", "Rambutan", "Banana")))
            ))

            val intArrayMap = mapOf(
                Pair("First Numbers", arrayOf(1, 4, 7, 3)),
                Pair("Second Numbers", arrayOf(751, 756, -435, 9))
            )

            testMap = mapOf(
                Pair("Strings", stringListMap),
                Pair("Ints", intArrayMap)
            )
    }

    @Test
    fun shouldFindTwoDeepList() {
        val list = mapFinder.findList(testMap!!, "Strings", "Greetings")
        assertThat(list?.size).isEqualTo(2)
        assertThat(list?.get(1)).isEqualTo("World")
    }

    @Test
    fun shouldFindThreeDeepList() {
        val list = mapFinder.findList(testMap!!, "Strings", "Groceries","Fruits")
        assertThat(list?.size).isEqualTo(3)
        assertThat(list?.last()).isEqualTo("Banana")
    }
}
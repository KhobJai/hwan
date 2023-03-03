package uk.intenso.hwan.strings

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class OldStrUtilsTest {


    @Test
    fun shouldDoubleQuote() {
        assertThat(doubleQuote("Hello")).isEqualTo("\"Hello\"")
    }

    @Test
    fun shouldSingleQuote() {
        assertThat(singleQuote("Goodbye")).isEqualTo("\'Goodbye\'")
    }

    @Test
    fun shouldDoubleQuoteAsDefault() {
        assertThat(quote("La Gorn Kaab")).isEqualTo("\"La Gorn Kaab\"")
    }
}
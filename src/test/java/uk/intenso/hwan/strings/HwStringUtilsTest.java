package uk.intenso.hwan.strings;

import org.junit.jupiter.api.*;

import static org.assertj.core.api.Assertions.*;

class HwStringUtilsTest {

    @Test
    void shouldDoubleQuote() {
        var result = HwStringUtils.doubleQuote("hello world");
        assertThat(result).isEqualTo("\"hello world\"");
    }

}
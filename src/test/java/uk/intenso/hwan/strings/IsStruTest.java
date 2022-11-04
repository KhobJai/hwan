package uk.intenso.hwan.strings;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Assertions.*;

import static org.assertj.core.api.Assertions.*;

class IsStruTest {

    @Test
    void shouldDoubleQuote() {
        var result = IsStru.doubleQuote("hello world");
        assertThat(result).isEqualTo("\"hello world\"");
    }
}
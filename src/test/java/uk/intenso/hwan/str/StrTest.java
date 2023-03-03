package uk.intenso.hwan.str;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class StrTest {


    private final String preFormatted = "My name is {name} and I am {age} years old, from {location}";

    @Test
    void shouldGetPlaceHolders() {
        var placeholders = Str.getPlaceholders(preFormatted);
        assertThat(placeholders).hasSize(3);
    }

    @Test
    void shouldFormat() {
        var formatted = Str.f(preFormatted, "John", 35, "Bournemouth");
        assertThat(formatted).isEqualTo("My name is John and I am 35 years old, from Bournemouth");
    }


}
package uk.intenso.hwan.ex;

import org.junit.jupiter.api.*;
import uk.intenso.hwan.TestException;

import static org.assertj.core.api.Assertions.*;

class ExUtilsTest {
    private final String TEST_EXCEPTION_MESSAGE = "Test Exception Message";
    @Test
    void shouldCreateRuntimeExceptionWithMessage() {

        var ex = ExUtils.create(TestException.class, TEST_EXCEPTION_MESSAGE);
        assertThat(ex.getMessage()).isEqualTo(TEST_EXCEPTION_MESSAGE);
        assertThat(ex.getClass()).isEqualTo(TestException.class);
    }

    @Test
    void shouldGetRootCause() {
        var ex = new Exception(new RuntimeException(new TestException(TEST_EXCEPTION_MESSAGE)));
        var rootCause = ExUtils.getRootCause(ex);
        assertThat(rootCause).isInstanceOf(TestException.class);
        assertThat(rootCause.getMessage()).isEqualTo(TEST_EXCEPTION_MESSAGE);
    }
}
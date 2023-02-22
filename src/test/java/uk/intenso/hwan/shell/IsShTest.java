package uk.intenso.hwan.shell;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Assertions.*;

import static org.assertj.core.api.Assertions.*;

class IsShTest {

    private final IsSh isSh = new IsSh();
    @Test
    void shouldExecCmd() {
        var isSuccessfulExec = isSh.exec("ls");
        assertThat(isSuccessfulExec).isTrue();
    }

    @Test
    void shouldFailToExecCmd() {
        var isSuccessfulExec = isSh.exec("lsdfsdf");
        assertThat(isSuccessfulExec).isFalse();
    }

    @Test
    void shouldReturnCurrentWorkingDir() {
        var response = isSh.query("pwd").trim();
        assertThat(response).isEqualTo(System.getProperty("user.dir"));
    }
    @Test
    void shouldSucceedWithMultipleCommands() {
        assertThat(isSh.exec("ls -la")).isTrue();
    }

}
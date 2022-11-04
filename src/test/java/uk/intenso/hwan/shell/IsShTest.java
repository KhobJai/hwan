package uk.intenso.hwan.shell;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Assertions.*;

import static org.assertj.core.api.Assertions.*;

class IsShTest {

    private IsSh isSh = new IsSh();
    @Test
    void shouldExecCmd() {
        var succcess = isSh.exec("ls");
        assertThat(succcess).isTrue();
    }

    @Test
    void shouldFailToExecCmd() {
        var succcess = isSh.exec("lsdfsdf");
        assertThat(succcess).isFalse();
    }

    @Test
    void shouldReturnCurrentWorkingDir() {
        var response = isSh.query("pwd").trim();
        System.out.println(response);
        assertThat(response).isEqualTo(System.getProperty("user.dir"));
    }

    @Test
    void shouldSucceedWithMultipleCommands() {
        assertThat(isSh.exec("ls -la")).isTrue();
    }

    @Test
    void shouldSucceedQueryWithMultipleCommands() {
        var response = isSh.query("nordvpn status");
        System.out.println(response);
        assertThat(response).isNotEmpty();
    }

}
package uk.intenso.hwan.shell;

import org.junit.jupiter.api.*;

import static org.assertj.core.api.Assertions.*;

class KotShTest {

    private final Sh sh = Sh.create();

    @Test
    void shouldExecCmd() {
        var isSuccessfulExec = sh.exec("ls");
        assertThat(isSuccessfulExec).isEqualTo(0);
    }

    @Test
    void shouldFailToExecCmd() {
     assertThatException().isThrownBy(() ->sh.exec("lsdfsdf"));
    }

    @Test
    void shouldReturnCurrentWorkingDir() {
        var response = sh.query("pwd").trim();
        assertThat(response).isEqualTo(System.getProperty("user.dir"));
    }

    @Test
    void shouldSucceedWithMultipleCommands() {
        assertThat(sh.exec("ls -la")).isEqualTo(0);
    }

}
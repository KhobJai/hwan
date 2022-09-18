package uk.intenso.hwan.dir;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
import uk.intenso.hwan.proc.ProcessUtils;

import java.io.IOException;

class DirUtilsTest {


    @Test
    void shouldGetHomeDir() {
        System.out.println(System.getProperty("user.home"));
        Assertions.assertThat(DirUtils.homeDir()).isEqualTo("/home/manjaro");
    }
    @Test
    void shouldGetCurrentDir() throws IOException {
//        assertThat(NativeUtils.exec("echo Hello > tmp.txt"));
        Assertions.assertThat(ProcessUtils.exec("echo Hello > /home/manjaro/tmp.txt"));
//        Runtime.getRuntime().exec("pwd");
//        assertThat(DirUtils.currentDir()).isEqualTo("/opt");
    }

}
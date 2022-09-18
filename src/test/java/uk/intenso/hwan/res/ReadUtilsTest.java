package uk.intenso.hwan.res;

import org.junit.jupiter.api.*;

import static org.assertj.core.api.Assertions.*;

class ReadUtilsTest {

    @Test
    void readFromClasspath() {
        assertThat(ReadUtils.readFromClasspath("testfile.txt")).isEqualTo("Hello Classpath");
    }

    @Test
    void readFromFilePath() {
        assertThat(ReadUtils.readFromFilePath(System.getProperty("user.home") + "/.test/testfile.txt").trim()).isEqualTo("Hello Filepath");
    }
}
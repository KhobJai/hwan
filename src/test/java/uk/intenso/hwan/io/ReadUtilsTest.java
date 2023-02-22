package uk.intenso.hwan.io;

import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ReadUtilsTest {

    @Test
    void getClasspathAsFile() {
        var file = ReadUtils.getFromClasspathAsFile("testdir");
        assertThat(file.isDirectory()).isTrue();
        assertThat(file.getAbsolutePath()).endsWith("resources/test/testdir");

    }

    @Test
    void getFileInDir() throws IOException {
        var file = ReadUtils.findFileInDirectory(ReadUtils.getFromClasspathAsFile("testdir"),"testfile.txt");
        assertNotNull(file);
        var str = IOUtils.toString(file.toURI(), StandardCharsets.UTF_8);
        assertThat(str).startsWith("ABC 123");
    }
    @Test
    void readFromClasspath() {
        assertThat(ReadUtils.readFromClasspath("testfile.txt")).isEqualTo("Hello Classpath");
    }

    @Test
    void readFromFilePath() {
        assertThat(ReadUtils.readFromFilePath(System.getProperty("user.home") + "/.test/testfile.txt").trim()).isEqualTo("Hello Filepath");
    }
}
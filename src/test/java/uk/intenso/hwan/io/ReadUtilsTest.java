package uk.intenso.hwan.io;

import org.apache.commons.io.IOUtils;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.*;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ReadUtilsTest {


    private static final String CLASSPATH_FILE_DATA = "Classpath file data";
    private static final String FILEPATH_FILE_DATA = "Filepath file data";

    private static final String TEST_FILE_DIRECTORY = System.getProperty("user.home") + "/.testFilepathDirectory";
    private static final String TEST_CLASSPATH_DIRECTORY = "TestClasspathDirectory";


    @NotNull
    private static String testClasspathFilename() {
        return "testClasspathFile.txt";
    }

    @NotNull
    private static String testFilepathFilename() {
        return "testFilepathFile.txt";
    }

    @NotNull
    private static String testFilePathFileFullPath() {
        return System.getProperty("user.home") + "/.testFilepathDirectory/testFilepathFile.txt";
    }

    @BeforeAll
    static void init() throws IOException {
        var path = Paths.get(TEST_FILE_DIRECTORY);
        Files.createDirectories(path);
    }

    @BeforeEach
    void setup() {
        HwFileIoUtils.writeStringToFile(FILEPATH_FILE_DATA, testFilePathFileFullPath());
    }

    @Test
    void shouldGetClasspathDirectory() {
        var file = HwFileIoUtils.getFromClasspathAsFile("TestClasspathDirectory");
        assertThat(file.isDirectory()).isTrue();
        assertThat(file.getAbsolutePath()).endsWith("resources/test/TestClasspathDirectory");

    }

    @Test
    void getFileInDir() throws IOException {
        var classpathDirectory = HwFileIoUtils.getFromClasspathAsFile(TEST_CLASSPATH_DIRECTORY);
        File file = HwFileIoUtils.findFileInDirectory(classpathDirectory, testClasspathFilename());
        assertNotNull(file);
        var str = IOUtils.toString(file.toURI(), StandardCharsets.UTF_8);
        assertThat(str).startsWith("Classpath file data");
    }

    @Test
    void readFromClasspath() {
        assertThat(HwFileIoUtils.readFromClasspath(TEST_CLASSPATH_DIRECTORY + "/" + testClasspathFilename())).isEqualTo(CLASSPATH_FILE_DATA);
    }

    @Test
    void readFromFilePath() throws IOException {
        try {
            HwFileIoUtils.writeStringToFile(FILEPATH_FILE_DATA, testFilePathFileFullPath());
            assertThat(HwFileIoUtils.readFromFilePath(testFilePathFileFullPath())).isEqualTo(FILEPATH_FILE_DATA);
        } catch (Exception e) {
            fail("Unable to read from file path", e);
        } finally {
            Files.delete(Paths.get(testFilePathFileFullPath()));
        }
    }

    @AfterEach
    void tearDown() {
        Paths.get(testFilePathFileFullPath()).toFile().delete();
    }


}
package uk.intenso;

import org.junit.jupiter.api.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import uk.intenso.hwan.io.HwFileIoUtils;
import uk.intenso.hwan.shell.CliUtils;
import uk.intenso.hwan.shell.Sh;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.assertj.core.api.Assertions.assertThat;

public class ShellCmdTest {

    private String password;

    private static final Logger log = LoggerFactory.getLogger(ShellCmdTest.class);


    private static final String TEST_DIR_NAME = "test_directory";

    private static final String TEST_FILE_NAME = "test_file.txt";


    private static final String EST_FILE_PATH = TEST_DIR_NAME  +"/"+TEST_DIR_NAME;

    private static final String TEST_ROOT_ABS_FILE_PATH = "/tmp/test_file.txt";
    private Sh javeSh;


    @BeforeAll
    static void setupAll() {
        System.setProperty("USER_PASSWORD", "mg33ageTrko");
    }

    @BeforeEach
    void setup() {
        password = System.getProperty("USER_PASSWORD");
        javeSh = password != null ? Sh.createSudo(password) : Sh.create();
        removeTestFiles();
    }

    @Test
    void shouldMkdir() {
        var exitCode = javeSh.exec("mkdir " + TEST_DIR_NAME);
        assertThat(exitCode).isEqualTo(0);
        checkDirectoryExists(CliUtils.currentDirectory() + "/" + TEST_DIR_NAME);
    }

    @Test
    void shouldQuery() {
        var result = javeSh.query("pwd");
        assertThat(result).isEqualTo(CliUtils.currentDirectory());
    }

    private static void checkDirectoryExists(String directoryPath) {
        Path path = Paths.get(directoryPath);
        assertThat(path).isNotNull();
        assertThat(path.toFile().isDirectory()).isTrue();
    }

    @Test
    void shouldPipe() {
        var result = javeSh.query("printenv | grep USER");
        assertThat(result).isEqualTo("USER=manjaro");
    }

    @Test
    void shouldRedirect() {
        var exitCode = javeSh.exec("echo 'Hello Redirect' > " + TEST_FILE_NAME);
        assertThat(exitCode).isEqualTo(0);

        var testFile = new File(TEST_FILE_NAME);
        assertThat(testFile.exists()).isTrue();
        var fileData = HwFileIoUtils.readFromFilePath(CliUtils.currentDirectory() + "/" + TEST_FILE_NAME);
        assertThat(fileData.trim()).isEqualTo("Hello Redirect");
    }


    @Test
    void shouldQuerySudo() {
        if (password == null) {
            log.info("Unable to run Sudo test as password not set");
            return;
        }
        assertThat(password).as("Password must be set to test running sudo commands",
                password).isNotNull();
        var rootDirContents = javeSh.querySudo("ls /root", password);
        assertThat(rootDirContents).contains("Applications");
    }

    @Test
    void shouldExecSudo() {
        if (password == null) {
            log.info("Unable to run Sudo test as password not set");
            return;
        }
        System.out.println("Creating File...");
        assertThat(javeSh.execSudo("touch " + TEST_ROOT_ABS_FILE_PATH, password)).isEqualTo(0);
        System.out.println("Changing Owner to root..");
        var exitCode = javeSh.execSudo("chown root:root " + TEST_ROOT_ABS_FILE_PATH, password);
        assertThat(exitCode).isEqualTo(0);
        System.out.println("Allowing user to read+write to file");
        exitCode = javeSh.execSudo("chmod  u+rw " + TEST_ROOT_ABS_FILE_PATH, password);
        assertThat(exitCode).isEqualTo(0);
        System.out.println("Writing to file!");
        exitCode = javeSh.execSudo("echo 'Hello Sudo' > " + TEST_ROOT_ABS_FILE_PATH, password);
        assertThat(exitCode).isEqualTo(0);
        var testFile = new File(TEST_FILE_NAME);
        assertThat(testFile.exists()).isTrue();
        var fileData = HwFileIoUtils.readFromFilePath(TEST_ROOT_ABS_FILE_PATH);
        assertThat(fileData.trim()).isEqualTo("Hellow Redirect");
    }


    @AfterEach
    void teardown() {
        removeTestFiles();
    }

    private void removeTestFiles() {
        if (new File(TEST_DIR_NAME).exists()) {
            assert javeSh.exec("rmdir " + TEST_DIR_NAME) == 0;
            if (password != null) {
                assert javeSh.execSudo("rm" + TEST_ROOT_ABS_FILE_PATH, password) == 0;
            }
        }

        if (password != null) {
            if (javeSh.querySudo("[ -d \"/root/" + TEST_DIR_NAME + "\" ] && echo 1", password).equals("1")) {
                assert javeSh.execSudo("rmdir " + "/root/" + TEST_DIR_NAME, password) == 0;
                assert javeSh.execSudo("rm -rg " + "/root/" + TEST_DIR_NAME, password) == 0;
            }
        }


        if (new File(TEST_FILE_NAME).exists()) {
            assert javeSh.exec("rm -f " + TEST_FILE_NAME) == 0;
        }
    }

    @AfterAll
    static void tearDown() {
        System.setProperty("USER_PASSWORD", "");
    }
}

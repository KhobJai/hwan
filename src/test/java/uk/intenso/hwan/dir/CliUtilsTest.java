package uk.intenso.hwan.dir;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.*;
import uk.intenso.hwan.shell.CliUtils;
import uk.intenso.hwan.shell.Sh;
import uk.intenso.hwan.shell.ShExec;

class CliUtilsTest {

    private static String user = "";


    @BeforeAll
    static void setup() {
        user = System.getenv("USER");
    }

    @Test
    void shouldReturnLoggedInUser() {
        assertThat(CliUtils.currentUser()).isEqualTo(user);
        assertThat(Sh.create().query("echo $USER")).isEqualTo(user);
    }


    @Test
    void shouldReturnCountryCode() {
        assertThat(CliUtils.countryCode()).isUpperCase();
        assertThat(CliUtils.countryCode().length()).isEqualTo(2);
    }

    @Test
    void shouldReturnSystemArchitecture() {
        var arch = CliUtils.systemArchitecture();
        System.out.println(arch);
    }
    @Test
    void shouldReturnTimeZone() {
        assertThat(CliUtils.timezone().split("/")).hasSize(2);
    }

    @Test
    void shouldGetHomeDir() {
        assertThat(CliUtils.homeDirectory()).isEqualTo("/home/%s".formatted(user));
    }

    @Test
    void shouldGetCurrentDir()  {
        assertThat(ShExec.exec("echo Hello > /home/%s/tmp.txt".formatted(user)));
    }


    @Test
    void shouldReturnCurrentLineSeparator() {
        var lineSep = CliUtils.lineSeparator();
        assertThat(lineSep).isEqualTo(System.getProperty("line.separator"));
    }


    @Test
    void shouldBuildFullFilePathForLinux() {
        System.setProperty("os.name", "Linux");
        var fullPath = CliUtils.buildFullPath("home", "UnixUser", "Downloads");
        assertThat(fullPath).isEqualTo("/home/UnixUser/Downloads");
    }

    @Test
    void shouldBuildFullFilePathForWindows() {
        System.setProperty("os.name", "Windows 10");
        assertThat(System.getProperty("os.name")).isEqualTo("Windows 10");
        assertThat(CliUtils.isWindows()).isTrue();
        var fullPath = CliUtils.buildFullPath("User", "WindowsUser", "Downloads");
        assertThat(fullPath).isEqualTo("C:\\\\User\\WindowsUser\\Downloads");
    }


    @Test
    void shouldDetectWindowsOs() {
        System.setProperty("os.name", "Windows 10");
        assertThat(CliUtils.isWindows()).isTrue();
    }

    @Test
    void shouldDetectNotWindows() {
        System.setProperty("os.name", "OS X");
        assertThat(CliUtils.isNotWindows()).isTrue();
    }

    @Test
    void shoudlDetectLinux() {
        System.setProperty("os.name", "Linux");
        assertThat(CliUtils.isLinux()).isTrue();
    }

    @Test
    void shouldDetectMac() {
        System.setProperty("os.name", "OS X");
        assertThat(CliUtils.isMac()).isTrue();
    }
}
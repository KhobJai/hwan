package uk.intenso.hwan.shell;


import java.io.File;
import java.nio.file.Path;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class CliUtils {

    private CliUtils() {
    }

    public static String homeDirectory() {
        return System.getProperty("user.home");
    }


    public static String currentDirectory() {
        return Path.of("").toAbsolutePath().toString();
    }

    public static String currentUser() {
        return System.getenv("USER");
    }

    public static String systemArchitecture() {
        return System.getProperty("os.arch");
    }

    public static String countryCode() {
        return System.getProperty("user.country");
    }

    public static String timezone() {
        return System.getProperty("user.timezone");
    }

    public static String operatingSystem() {
        return System.getProperty("os.name");
    }

    public static boolean isNotWindows() {
        return !isWindows();
    }

    public static boolean isWindows() {
        return operatingSystem().toLowerCase().contains("windows");
    }

    public static boolean isMac() {
        return operatingSystem().toLowerCase().contains("os x");
    }

    public static boolean isLinux() {
        return operatingSystem().toLowerCase().contains("linux");
    }

    public static String lineSeparator() {
        if (isWindows()) return "\r\n";
        else return "\n";
    }

    public static String buildFullPath(String... parts) {
        var prefix = isLinux() ? "/" : "C:\\\\";
        var fileSeparator = isLinux() ? "/" : "\\";
        return prefix + Stream.of(parts).collect(Collectors.joining(fileSeparator));
    }

}

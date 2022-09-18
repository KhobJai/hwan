package uk.intenso.hwan.dir;


import java.nio.file.Path;

public class DirUtils {

    private DirUtils() {
    }

    public static String homeDir() {
        return System.getProperty("user.home");
    }


    public static String currentDir() {
        return Path.of("").toAbsolutePath().toString();
    }
}

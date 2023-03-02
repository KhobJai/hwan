package uk.intenso.hwan.io;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.function.Supplier;

public final class HwIoUtils {
    private HwIoUtils() {
    }

    public static File findFileInDirectory(File directory, String filename) {
        return ReadIO.findFileInDirectory(directory, filename);
    }

    public static File classpathRootAsFile() {
        return ReadIO.classpathRootAsFile();
    }

    public static File getFromClasspathAsFile(String path) {
        return ReadIO.getFromClasspathAsFile(path);
    }

    public static String readFromClasspath(String path) {
        return ReadIO.readFromClasspath(path);
    }

    public static String readFromFilePath(String path) {
        return ReadIO.readFromFilePath(path);
    }

    public static String streamToString(Supplier<InputStream> sup) {
        return ReadIO.streamToString(sup);
    }

    public static String streamToString(InputStream stream) {
        return ReadIO.streamToString(stream);
    }


    public static InputStream classpathToStream(String path) {
        return ReadIO.classpathToStream(path);
    }


    public static void writeStringToFile(String data, String path) {
        WriteIO.writeStringToFile(data, path);
    }


    public static void writeInputToStdOut(InputStream is) {
        WriteIO.writeInputToStdOut(is);
    }


    public static void writeToStream(InputStream is, OutputStream os) {
        WriteIO.writeToStream(is, os);
    }
}

package uk.intenso.hwan.io;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.function.Supplier;

public final class HwFileIoUtils {
    private HwFileIoUtils() {
    }

    public static File findFileInDirectory(File directory, String filename) {
        return FileReader.findFileInDirectory(directory, filename);
    }

    public static File classpathRootAsFile() {
        return FileReader.classpathRootAsFile();
    }

    public static File getFromClasspathAsFile(String path) {
        return FileReader.getFromClasspathAsFile(path);
    }

    public static String readFromClasspath(String path) {
        return FileReader.readFromClasspath(path);
    }

    public static String readFromFilePath(String path) {
        return FileReader.readFromFilePath(path);
    }

    public static String streamToString(Supplier<InputStream> sup) {
        return FileReader.streamToString(sup);
    }

    public static String streamToString(InputStream stream) {
        return FileReader.streamToString(stream);
    }


    public static InputStream classpathToStream(String path) {
        return FileReader.classpathToStream(path);
    }


    public static void writeStringToFile(String data, String path) {
        FileWriter.writeStringToFile(data, path);
    }


    public static void writeInputToStdOut(InputStream is) {
        FileWriter.writeInputToStdOut(is);
    }


    public static void writeToStream(InputStream is, OutputStream os) {
        FileWriter.writeToStream(is, os);
    }
}

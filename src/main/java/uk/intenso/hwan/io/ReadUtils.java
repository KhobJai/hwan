package uk.intenso.hwan.io;

import org.apache.commons.io.IOUtils;
import uk.intenso.hwan.ex.TryUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.function.Supplier;
import java.util.stream.Stream;

public abstract class ReadUtils {

    public static File findFileInDirectory(File dirFile,String filename) {
        try (Stream<Path> paths = Files.walk(dirFile.toPath())) {
          return   paths
                    .filter(Files::isRegularFile)
                    .filter(p -> p.getFileName().toString().equals(filename))
                    .findFirst().orElseThrow().toFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static File classpathRootAsFile() {
        return getFromClasspathAsFile(".");
    }
    public static File getFromClasspathAsFile(String path) {
        return TryUtils.orThrow(() -> Paths.get(Objects.requireNonNull(ReadUtils.class.getClassLoader().
                getResource(path)).toURI()).toFile());
    }
    public static String readFromClasspath(String path) {
        return TryUtils.orThrow(() -> streamToString(Objects.requireNonNull(ReadUtils.class.getClassLoader()
                .getResourceAsStream(path))));
    }

    public static String readFromFilePath(String path) {
        return TryUtils.orThrow(() -> Files.readString(Path.of(path)));
    }

    public static String streamToString(Supplier<InputStream> sup) {
        return streamToString(sup.get());
    }

    public static String streamToString(InputStream stream) {
        return TryUtils.orThrow(() -> IOUtils.toString(stream, StandardCharsets.UTF_8));

    }

    public static InputStream classpathToStream(String path) {
        return TryUtils.orThrow(() -> ReadUtils.class.getClassLoader().getResourceAsStream(path));
    }


}

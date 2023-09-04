package uk.intenso.hwan.io;

import org.apache.commons.io.IOUtils;
import uk.intenso.hwan.hwtry.statictry.TryUtils;

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

 interface FileReader {

    static File findFileInDirectory(File directory, String filename) {
        try (Stream<Path> paths = Files.walk(directory.toPath())) {
            return paths
                    .filter(Files::isRegularFile)
                    .filter(p -> p.getFileName().toString().equals(filename))
                    .findFirst().orElseThrow().toFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static File classpathRootAsFile() {
        return getFromClasspathAsFile(".");
    }

    static File getFromClasspathAsFile(String path) {
        return TryUtils.orThrow(() -> Paths.get(Objects.requireNonNull(HwFileIoUtils.class.getClassLoader().
                getResource(path)).toURI()).toFile());
    }

    static String readFromClasspath(String path) {
        return TryUtils.orThrow(() -> streamToString(Objects.requireNonNull(HwFileIoUtils.class.getClassLoader()
                .getResourceAsStream(path))));
    }

    static String readFromFilePath(String path) {
        return TryUtils.orThrow(() -> Files.readString(Path.of(path)));
    }

    static String streamToString(Supplier<InputStream> sup) {
        return streamToString(sup.get());
    }

    static String streamToString(InputStream stream) {
        return TryUtils.orThrow(() -> IOUtils.toString(stream, StandardCharsets.UTF_8));

    }

    static InputStream classpathToStream(String path) {
        return TryUtils.orThrow(() -> HwFileIoUtils.class.getClassLoader().getResourceAsStream(path));
    }
}

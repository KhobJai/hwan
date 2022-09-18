package uk.intenso.hwan.res;

import org.apache.commons.io.IOUtils;
import uk.intenso.hwan.ex.TryUtils;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;
import java.util.function.Supplier;

public abstract class ReadUtils {

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

package uk.intenso.hwan.hwtry.statictry;

import java.lang.reflect.Constructor;
import java.util.function.Supplier;

public class ExUtils {

    private ExUtils() {
    }

    public static <T extends RuntimeException> Supplier<T> supply(Class<T> clazz, String message) {
        return () -> create(clazz, message);
    }

    public static <T extends RuntimeException> Supplier<T> supply(Class<T> clazz, String message, Throwable cause) {
        return () -> create(clazz, message, cause);
    }

    public static <T extends RuntimeException> T create(Class<T> clazz, String message) {
        for (Constructor<?> constructor : clazz.getConstructors()) {
            if (constructor.getParameterTypes().length == 1 && constructor.getParameterTypes()[0].equals(String.class)) {
                return create(constructor, message);
            }
        }
        throw new ConstructorNotFoundException("Appropriate Constructor Not Found..");
    }

    public static <T extends RuntimeException> T create(Class<T> clazz, String message, Throwable cause) {
        for (Constructor<?> constructor : clazz.getConstructors()) {
            if (constructor.getParameterTypes().length == 2 && constructor.getParameterTypes()[0].equals(String.class)
                    && constructor.getParameterTypes()[1].equals(Throwable.class)) {
                return create(constructor, message, cause);
            }
        }
        throw new ConstructorNotFoundException("Appropriate Constructor Not Found..");
    }

    public static Throwable getRootCause(Throwable t) {
        var cause = t.getCause();
        if (cause != null) {
            return getRootCause(cause);
        } else {
            return t;
        }
    }


    private static <T extends RuntimeException> T create(Constructor<?> cons, Object... args) {
        return (T) TryUtils.orThrow(() -> cons.newInstance(args));
    }
}

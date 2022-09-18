package uk.intenso.hwan.ex;

import java.util.function.Supplier;

public class TryUtils {

    private TryUtils() {
    }

//    public static <U> U orThrow(Supplier<U> sup) {
//        return orThrow(wrap(sup));
//    }

    public static <U> U orThrow(TrySup<U> sup) {
        return orThrow(sup, null);
    }

    public static <U> U orThrow(TrySup<U> sup, String message) {
        try {
            var rex = wrapThrowable(sup);
            return tryGet(rex);
        } catch (Exception e) {
            if (message != null) {
                throw new RuntimeException(message, e);
            } else throw new RuntimeException(e);
        }
    }


    public static <T> RexTrySup<T> wrap(Supplier<T> sup) {
        return new RexTrySup<T>() {
            @Override
            public T get() throws RuntimeException {
                try {
                    return sup.get();
                } catch (Throwable e) {
                    throw new RuntimeException(e);
                }
            }
        };
    }

    public static <T> RexTrySup<T> wrapThrowable(TrySup<T> sup) {
        return new RexTrySup<T>() {
            @Override
            public T get() throws RuntimeException {
                try {
                    return sup.get();
                } catch (Throwable e) {
                    throw new RuntimeException(e);
                }
            }
        };
    }

    public static <T> RexTrySup<T> wrapChecked(ExTrySup<T> sup) {
        return new RexTrySup<T>() {
            @Override
            public T get() throws RuntimeException {
                try {
                    return tryGet(wrapThrowable(sup));
                } catch (Throwable e) {
                    throw new RuntimeException(e);
                }
            }
        };
    }

    static <T> T tryGet(RexTrySup<T> sup) {
        return sup.get();
    }
}
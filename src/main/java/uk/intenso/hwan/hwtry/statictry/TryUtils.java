package uk.intenso.hwan.hwtry.statictry;

import uk.intenso.hwan.hwtry.TryException;

import java.util.function.Supplier;

public class TryUtils {

    private TryUtils() {
    }


    public static <T> T orThrow(TrySupplier<T> sup) {
        return orThrow(sup, null);
    }

    public static <T> T orThrow(TrySupplier<T> sup, String message) {
        try {
            var rex = wrapThrowable(sup);
            return tryGet(rex);
        } catch (Exception e) {
            if (message != null) {
                throw new TryException(message, e);
            } else return throwTryException(e);
        }
    }


    public static <T> RexTrySupplier<T> wrap(Supplier<T> sup) {
        return new RexTrySupplier<T>() {
            @Override
            public T get() {
                try {
                    return sup.get();
                } catch (Throwable e) {
                    return throwTryException(e);
                }
            }
        };
    }

    public static <T> RexTrySupplier<T> wrapThrowable(TrySupplier<T> sup) {
        return new RexTrySupplier<T>() {
            @Override
            public T get() throws TryException {
                try {
                    return sup.get();
                } catch (Throwable e) {
                    return throwTryException(e);
                }
            }
        };
    }

    public static <T> RexTrySupplier<T> wrapChecked(ExTrySupplier<T> sup) {
        return new RexTrySupplier<T>() {
            @Override
            public T get() throws TryException {
                try {
                    return tryGet(wrapThrowable(sup));
                } catch (Throwable e) {
                    return throwTryException(e);
                }
            }
        };
    }

    static <T> T throwTryException(Throwable e) {
        throw new TryException("Unable to get value", e);
    }

    static <T> T tryGet(RexTrySupplier<T> sup) {
        return sup.get();
    }
}
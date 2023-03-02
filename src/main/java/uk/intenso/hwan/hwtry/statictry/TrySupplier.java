package uk.intenso.hwan.hwtry.statictry;

public interface TrySupplier<T> {
    T get() throws Throwable;
}

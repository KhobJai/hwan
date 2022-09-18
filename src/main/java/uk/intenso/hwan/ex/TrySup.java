package uk.intenso.hwan.ex;

public interface TrySup<T> {
    T get() throws Throwable;
}

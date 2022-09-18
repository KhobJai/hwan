package uk.intenso.hwan.ex;

@FunctionalInterface
public interface ExTrySup<T> extends TrySup<T> {
    @Override
    T get() throws Exception;
}

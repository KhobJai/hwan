package uk.intenso.hwan.ex;

public interface RexTrySup<T>  extends ExTrySup<T> {


    @Override
    T get() throws RuntimeException;
}

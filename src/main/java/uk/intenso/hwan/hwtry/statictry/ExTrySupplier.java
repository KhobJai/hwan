package uk.intenso.hwan.hwtry.statictry;

import uk.intenso.hwan.hwtry.statictry.TrySupplier;

@FunctionalInterface
public interface ExTrySupplier<T> extends TrySupplier<T> {
    @Override
    T get() throws Exception;
}

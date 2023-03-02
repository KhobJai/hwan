package uk.intenso.hwan.hwtry.statictry;

import uk.intenso.hwan.hwtry.TryException;

public interface RexTrySupplier<T>  extends ExTrySupplier<T> {


    @Override
    T get() throws TryException;
}

package uk.intenso.hwan.hwtry.monadtry;

import java.util.NoSuchElementException;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class Try<T> {


    private Exception exception;
    private T value;


    private Try(Exception exception) {
        this.exception = exception;
    }


    private Try(T value) {
        this.value = value;
    }

    public Try(Supplier<T> action) {

        try {
            value = action.get();
        } catch (Exception e) {
            exception = e;
        }

    }

    public void orThrow(Supplier<RuntimeException> exSup) {
        var customException = exSup.get();
        throw (RuntimeException) customException.initCause(exception);
    }

    public T get() {
        if (value == null) {
            throw new NoSuchElementException("No value present");
        }
        return value;
    }


    public boolean isSuccess() {
        return value != null;
    }


    public boolean hasExceptionBeenThrown() {
        return exception != null;
    }

    public Try<T> onSuccess(Consumer<? super T> action) {
        if (value != null) {
            action.accept(value);
        }
        return this;
    }

    public Try<T> onException(Consumer<? super Exception> exceptionAction) {
        if (exception != null) {
            exceptionAction.accept(exception);
        }
        return this;
    }
}

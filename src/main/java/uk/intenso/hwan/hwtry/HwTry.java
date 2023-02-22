package uk.intenso.hwan.hwtry;

import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class HwTry {

    private static Logger log = LoggerFactory.getLogger(HwTry.class);

    private HwTry() {
    }


    public <T> Optional<T> forOptional(Supplier<T> supplier) {
        try {
            return Optional.of(supplier.get());
        } catch (Exception e) {
            return logAndReturnEmpty(e);
        }
    }

    public <T, R> Optional<R> forOptional(Function<T, R> fun, T input) {
        try {
            return Optional.of(fun.apply(input));
        } catch (Exception e) {
            return logAndReturnEmpty(e);
        }
    }

    public <T> void orLog(Consumer<T> cons, T input) {
        try {
            cons.accept(input);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    @NotNull
    private <R> Optional<R> logAndReturnEmpty(Exception e) {
        log.error(e.getMessage(), e);
        return Optional.empty();
    }
}

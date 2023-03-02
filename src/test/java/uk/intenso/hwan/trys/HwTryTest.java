package uk.intenso.hwan.trys;

import org.junit.jupiter.api.*;
import uk.intenso.hwan.hwtry.statictry.TrySupplier;
import uk.intenso.hwan.hwtry.statictry.TryUtils;

import static org.assertj.core.api.Assertions.*;

class HwTryTest {

    TrySupplier<Integer> goodTrySupplier = () -> 1 + 2;
    TrySupplier<Short> badTrySupplier = () ->  Short.valueOf("3180743298w597893983758");



    @Test
    void shouldSucceedOnGoodTrySup() {
        assertThat(goodTrySupplier).isInstanceOf(TrySupplier.class);
        assertThat(TryUtils.orThrow(goodTrySupplier)).isEqualTo(3);
    }



//    @Test
//    void shouldThrowOnBadTrySup() {
//        assertThat(badTrySup).isInstanceOf(TrySup.class);
//
//        try {
//            TryUtils.orThrow(badTrySup);
//            fail("Didn't throw exception");
//        } catch (RuntimeException e) {
//            assertThat(e).isInstanceOf(RuntimeException.class);
//            assertThat(e.getCause()).isInstanceOf(NumberFormatException.class);
//        }
//    }

}
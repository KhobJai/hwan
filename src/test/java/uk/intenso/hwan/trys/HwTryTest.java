package uk.intenso.hwan.trys;

import org.junit.jupiter.api.*;
import uk.intenso.hwan.ex.TrySup;
import uk.intenso.hwan.ex.TryUtils;

import static org.assertj.core.api.Assertions.*;

class HwTryTest {

    TrySup<Integer> goodTrySup = () -> 1 + 2;
    TrySup<Short> badTrySup = () ->  Short.valueOf("3180743298w597893983758");



    @Test
    void shouldSucceedOnGoodTrySup() {
        assertThat(goodTrySup).isInstanceOf(TrySup.class);
        assertThat(TryUtils.orThrow(goodTrySup)).isEqualTo(3);
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
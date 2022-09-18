package uk.intenso.hwan.perf;

import org.junit.jupiter.api.*;

import static org.assertj.core.api.Assertions.*;

class IsTimerTest {


    @Test
     void shouldTimeThreeSecondsInSeconds() throws InterruptedException {
        var timer = IsTimer.create();
        waitForMillis(3000);
        var timeInSeconds = timer.getTimeTakenInSecondsAsString();
        assertThat(timeInSeconds).isEqualTo("3.00");
    }

    @Test
     void shouldTimeTwoSecondsInMilliSeconds() throws InterruptedException {
        var timer = IsTimer.create();
        waitForMillis(2000);
        var timeInSeconds = timer.getTimeTakenInMillisecondsAsString();
        assertThat(timeInSeconds).startsWith("2000.");
    }

    private void waitForMillis(long millis) throws InterruptedException {
        Thread.sleep(millis);
    }

}
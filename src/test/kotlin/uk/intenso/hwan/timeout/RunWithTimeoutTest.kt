package uk.intenso.hwan.timeout

import Pred
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import uk.intenso.hwan.timing.HwWatch
import java.util.function.Function

internal class RunWithTimeoutTest {


    @Test
    fun waitUntilCompleteTest() {
        val pred: Pred = testPred()

        val testLongFunction =
            Function { i: Int? -> Thread.sleep(1500);"$i" }
        val timer = HwWatch.create()
        RunWithTimeout.untilComplete(testLongFunction,2000,10)
        val timeTaken = timer.timeTakenInMilliseconds()
        println("Time Taken: ${timer.timeTakenInMillisecondsAsString}")
        assertThat(timeTaken).isLessThan(2100.00)
    }

    private fun testPred(): Pred {
        return {
            Thread.sleep(1980)
            println("Test Pred Finished")
            true
        }
    }
}
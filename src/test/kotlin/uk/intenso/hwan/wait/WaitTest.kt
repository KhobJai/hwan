package uk.intenso.hwan.wait

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.*
import uk.intenso.hwan.kottest.Pred
import uk.intenso.hwan.perf.IsTimer

internal class WaitTest {


    @Test
    fun waitUntilCompleteTest() {
        val pred: Pred = testPred()

        val timer = IsTimer.create()
        Wait.untilComplete(pred,2000)
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
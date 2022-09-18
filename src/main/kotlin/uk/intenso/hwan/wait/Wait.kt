package uk.intenso.hwan.wait

import uk.intenso.hwan.kottest.Pred


object Wait {

    fun waitUntilComplete(pred:Pred, maxWaitIntMillis:Int, interval:Long=10) {
        var currentMillis = 0L
        while (!pred()) {
            Thread.sleep(interval)
            currentMillis +=interval
            if (currentMillis >= maxWaitIntMillis) {
                break
            }
        }
    }
}
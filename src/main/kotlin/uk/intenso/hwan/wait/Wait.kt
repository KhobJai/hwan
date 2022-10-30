package uk.intenso.hwan.wait

import uk.intenso.hwan.kottest.Pred
import java.util.function.Predicate


object Wait {

    fun untilComplete(pred: Pred, maxWaitIntMillis: Int, interval: Long = 10) {
        var currentMillis = 0L
        while (!pred()) {
            Thread.sleep(interval)
            currentMillis += interval
            if (currentMillis >= maxWaitIntMillis) {
                break
            }
        }
    }

    fun <T> untilTrue(pred: Predicate<T>, t: T, maxWaitIntMillis: Int, interval: Long = 10) {
        var currentMillis = 0L
        while (!pred.test(t)) {
            Thread.sleep(interval)
            currentMillis += interval
            if (currentMillis >= maxWaitIntMillis) {
                break
            }
        }
    }

    fun untilTrue(pred: Pred, maxWaitIntMillis: Int = 1000, interval: Long = 10) {
        var currentMillis = 0L
        while (pred()) {
            Thread.sleep(interval)
            currentMillis += interval
            if (currentMillis >= maxWaitIntMillis) {
                break
            }
        }
    }

    fun untilFalse(pred: Pred, maxWaitIntMillis: Int = 1000, interval: Long = 10) {
        untilTrue({ !pred() })
    }
}
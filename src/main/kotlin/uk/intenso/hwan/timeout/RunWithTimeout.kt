package uk.intenso.hwan.timeout

import Pred
import java.util.concurrent.Executors
import java.util.concurrent.Future
import java.util.concurrent.TimeUnit
import java.util.function.Function
import java.util.function.Predicate


/**
 * Run function calls that are not expected to return immediately, such as:
 * 1. Database calls
 * 2. Network calls
 * 3. Native command calls
 *
 * waitnig a maximum length of time before exiting
 */
object RunWithTimeout {

    @JvmStatic
    fun <T,R> untilComplete(function: Function<T,R>, t:T, maxWaitIntMillis: Long):R? {
        val executor = Executors.newSingleThreadExecutor()
        val future: Future<*> = executor.submit { function.apply(t) }
        try {
            future[maxWaitIntMillis, TimeUnit.MILLISECONDS]
        } catch (e: Exception) {
            future.cancel(true)
            throw TimeoutException(e);
        } finally {
            executor.shutdownNow()
        }
        return (future.get() as R?)
    }

    @JvmStatic
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

    @JvmStatic
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

    @JvmStatic
    fun untilFalse(pred: Pred, maxWaitIntMillis: Int = 1000, interval: Long = 10) {
        untilTrue({ !pred() },maxWaitIntMillis,interval)
    }
}
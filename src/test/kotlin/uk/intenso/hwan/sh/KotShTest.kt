package uk.intenso.hwan.sh

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.fail
import org.junit.jupiter.api.Test
import uk.intenso.hwan.io.HwIoUtils
import uk.intenso.hwan.timeout.RunWithTimeout

internal class KotShTest {

    val sudoArray = arrayOf("sh -c")

    val basicLs = "ls /home/manjaro"
    val lsArgs = "ls -la /home/manjaro"

    val pipePs = arrayOf("sh -c ps -ef |grep nord")
    val pipeLs = arrayOf("ls", "-ls|", "Down")


    @Test
    fun processSandbox() {

        var currentProc: Process? = null;

        pipePs.forEach {
            try {
                val proc = Runtime.getRuntime().exec(it);
                currentProc = proc

                RunWithTimeout.untilFalse(proc::isAlive, 5500)
                println("Proceess  is Alive? ${proc.isAlive}")
                proc.waitFor();
                if (proc.exitValue() != 0) {
                    println(HwIoUtils.streamToString(proc.errorStream))
                    fail<String>("Error Exit Value: ${proc.exitValue()}")
                }
                val test = HwIoUtils.streamToString(proc.inputStream);
                assertThat(test).isNotEmpty;
                println(test)

            } catch (e: java.lang.Exception) {
                e.printStackTrace()
            } finally {
                currentProc?.let {
                    currentProc!!.destroyForcibly()
                }
            }
        }
    }
}

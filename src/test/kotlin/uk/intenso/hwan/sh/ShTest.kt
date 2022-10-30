package uk.intenso.hwan.sh

import org.apache.commons.io.IOUtils
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.*
import org.assertj.core.api.Assertions.*
import uk.intenso.hwan.kottest.Pred
import uk.intenso.hwan.res.ReadUtils
import uk.intenso.hwan.wait.Wait
import java.util.Properties
import java.util.function.Predicate

internal class ShTest {

    val sudoArray = arrayOf("sh -c")

    val basicLs = "ls /home/manjaro"
    val lsArgs = "ls -la /home/manjaro"

    val pipePs = arrayOf("sh -c ps -ef |grep nord")
    val pipeLs = arrayOf("ls", "-ls|", "Down")


    @Test
    fun processSandbox() {

        var currentProc:Process? = null;

            pipePs.forEach {
                try {
               val proc = Runtime.getRuntime().exec(it);
                    currentProc = proc
//            proc = Runtime.getRuntime().exec(pipePs);

                Wait.untilFalse(proc::isAlive, 5500)
                    println("Proceess  Status: ${proc.isAlive}")
                if (proc.exitValue() != 0) {
                    println(ReadUtils.streamToString(proc.errorStream))
                    fail("Error Exit Value: ${proc.exitValue()}")
                }
                val test = ReadUtils.streamToString(proc.inputStream);
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

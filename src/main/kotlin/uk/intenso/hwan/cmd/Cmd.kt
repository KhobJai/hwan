package uk.intenso.hwan.cmd

import org.apache.commons.io.IOUtils
import uk.intenso.hwan.res.ReadUtils
import java.util.Arrays
import java.util.concurrent.TimeUnit


fun quote(str: String): String {
    return '"' + str + '"'
}

fun runCommand(cmd: String): String? {
    val fullCommand = "sh -c" + quote(cmd)
    val proc = Runtime.getRuntime().exec(fullCommand)
    return ReadUtils.streamToString(proc.inputStream)
}

fun exec(vararg cmd: String, waitInMillis: Long = 0): String {
    val arr: Array<String> = arrayOf(*cmd)
    return exec(arr, waitInMillis, false)
}

fun exec(cmds: String, waitInMillis: Long = 0): String {
    val arr: Array<String> = cmds.split(" ").toTypedArray()
    return exec(arr, waitInMillis, false)
}

fun exec(cmd: Array<String>, waitInMillis: Long = 1000, destroy: Boolean = false): String {
    var proc: Process? = null;
    try {
        val cmdString = cmd.joinToString(" ")
        println("Executing command: $cmdString")
        proc = Runtime.getRuntime().exec(cmd)
        proc.waitFor(waitInMillis, TimeUnit.MILLISECONDS)
        while (proc.isAlive) {
        }
        val exitCode = proc.exitValue();
        println("Exit Value: $exitCode")
        if (exitCode == 0) {
            println("Command Completed Successfully")
        }
        return ReadUtils.streamToString(proc.inputStream)
    } catch (e: Exception) {
        throw RuntimeException(e)
    } finally {
        if (destroy) proc?.destroyForcibly();
    }
}
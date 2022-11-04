package uk.intenso.hwan.shell;

import org.apache.commons.io.IOUtils;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import uk.intenso.hwan.ex.TryUtils;
import uk.intenso.hwan.res.ReadUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Objects;

public  class IsSh {

    private  static Logger log = LoggerFactory.getLogger(IsSh.class);

    private  static final String LINE_BREAK = "n------------%n";

    public  String query(String cmd) {
        String response = "";
        Process proc = null;
        String fullCommand = buildCommand(cmd);
        logCommand(fullCommand);
        try {
            proc = buildProc(fullCommand);
            runAndWait(proc);
            assertExitValueIsZero(proc);
            return getOutput(proc);
        } catch (Exception e) {
            assert proc != null;
            response = getErrorReader(proc);
            logError(e, response);
            return response;
        } finally {
            destroyProcess(Objects.requireNonNull(proc));
        }
    }

    private  Process buildProc(String fullCommand) throws IOException {
        return Runtime.getRuntime().exec(fullCommand);
    }

    private  Process buildProc(String[] fullCommand) throws IOException {
        return Runtime.getRuntime().exec(new String[] {"sh -c","nordvpn","status"});
    }

    public  boolean exec(String cmd) {

        String response = "";
        Process proc = null;
        String fullCommand = buildCommand(cmd);
        logCommand(fullCommand);
        try {
            proc = buildProc(fullCommand);
            runAndWait(proc);
            getOutput(proc);
            assertExitValueIsZero(proc);
            return true;
        } catch (Exception e) {
           log.error(e);
            Process finalProc = proc;
             response = TryUtils.orThrow(() -> getErrorReader(finalProc));
            logError(e, response);
            return false;
        } finally {
            destroyProcess(Objects.requireNonNull(proc));
        }

    }

    private  void runAndWait(Process proc) throws Exception {
        waitUntilProcessComplete(proc);
    }

    private  void destroyProcess(Process proc) {
        assert proc != null;
        proc.destroy();
    }

    private  void logError(Exception e, String response) {
        System.out.printf(LINE_BREAK);
        log.error(response, e);
        System.out.printf(LINE_BREAK);
    }

    private  String getErrorReader(Process proc) {
        Reader errinput = new BufferedReader(new InputStreamReader(
                proc.getInputStream()));
        try {
            return IOUtils.toString(errinput);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private  void waitUntilProcessComplete(Process proc) throws InterruptedException {
        System.out.printf("Process with PID: %d started%n", proc.pid());
        proc.waitFor();
    }

    private  void assertExitValueIsZero(Process proc) {
        if (proc.exitValue() != 0) {
            log.warn("Process Failed...");
            log.error("Output: \n + " getOutput(proc);
            log.error("Error: \n + " getErrorOutput(proc);
            throw new RuntimeException("Exit Value is " + proc.exitValue() + " Expecting 0");
        }
    }

    private  String getOutput(Process proc) {
        return  ReadUtils.streamToString(proc::getInputStream);
    }

    private  String getErrorOutput(Process proc) {
        return ReadUtils.streamToString(proc::getErrorStream);
    }
    private  void logCommand(String fullCommand) {
        log.info("Command to be run: {}", fullCommand);
    }
}

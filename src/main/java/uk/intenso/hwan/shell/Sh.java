package uk.intenso.hwan.shell;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import uk.intenso.hwan.io.HwIoUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Sh implements ShellCmdExecutor {

    private String password = null;
    private static Logger log = LoggerFactory.getLogger(Sh.class);

    private final List<String> CMD_PREFIX = new ArrayList<>(List.of("/bin/sh", "-c"));

    private Sh() {
    }

    public static Sh create() {
        return new Sh();
    }
    public static Sh createSudo(String password) {
        var sh = new Sh();
        sh.password=password;
        return sh;
    }
    public String querySudo(String cmd, String password) {
        return query("echo %s| sudo -S %s".formatted(password, cmd));
    }

    public int execSudo(String cmd, String password) {
        return exec("echo %s| sudo -S %s".formatted(password, cmd));
    }

    public String query(String cmd) {
        String response = "";
        Process proc = null;
        logCommand("(Query) - " + cmd);
        try {
            proc = buildProc(cmd);
            runAndWait(proc);
            assertExitValueIsZero(proc);
            return getOutput(proc).trim();
        } catch (Exception e) {
            assert proc != null;
            response = getErrorReader(proc);
            logError(e, response);
            return response;
        } finally {
            destroyProcess(Objects.requireNonNull(proc));
        }
    }

    private Process buildProc(String cmd) throws IOException {
        String[] fullCommand = {
                CMD_PREFIX.get(0),
                CMD_PREFIX.get(1),
                cmd
        };
        return Runtime.getRuntime().exec(fullCommand);
    }


    public int exec(String cmd) {

        String response = "";
        Process proc = null;
        List<String> fullCommand = Stream.concat(CMD_PREFIX.stream(), Stream.of(cmd)).toList();
        logCommand("(Exec) - " + fullCommand);
        try {
            proc = buildProc(cmd);
            runAndWait(proc);
            getOutput(proc);
            assertExitValueIsZero(proc);
            return 0;
        } catch (Exception e) {
            throw new ShellCommandException(getErrorOutput(proc),e);
//            log.error("Command Failed - " + getErrorOutput(proc), e);
//            return proc != null ? proc.exitValue() : 1;
        } finally {
            if (proc != null) {
                destroyProcess(Objects.requireNonNull(proc));
            }
        }

    }

    private void runAndWait(Process proc) throws Exception {
        waitUntilProcessComplete(proc);
    }

    private void destroyProcess(Process proc) {
        assert proc != null;
        proc.destroy();
    }

    private void logError(Exception e, String response) {
        log.error(response, e);
    }

    private String getErrorReader(Process proc) {
        Reader errinput = new BufferedReader(new InputStreamReader(
                proc.getInputStream()));
        try {
            return IOUtils.toString(errinput);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void waitUntilProcessComplete(Process proc) throws InterruptedException {
        proc.waitFor();
    }

    private void assertExitValueIsZero(Process proc) {
        if (proc.exitValue() != 0) {
//            log.trace("Process Failed - Output: \n {} \n Error Output: \n {}", getOutput(proc), getErrorOutput(proc));
            throw new ShellCommandException("Process failed to execute command");
        }
    }

    private String getOutput(Process proc) {
        return HwIoUtils.streamToString(proc::getInputStream);
    }

    private String getErrorOutput(Process proc) {
        return HwIoUtils.streamToString(proc::getErrorStream);
    }

    private void logCommand(String fullCommand) {
        log.info("Command to be run: {}", fullCommand);
    }

    private void logCommand(String[] fullCommand) {
        var cmdString = Stream.of(fullCommand).collect(Collectors.joining(" "));
        log.info("Command to be run: {}", cmdString);
    }

    private void logCommand(List<String> fullCommand) {
        var cmdString = fullCommand.stream().collect(Collectors.joining(" "));
        log.info("Command to be run: {}", cmdString);
    }
}
